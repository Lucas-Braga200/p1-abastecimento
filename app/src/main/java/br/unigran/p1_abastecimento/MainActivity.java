package br.unigran.p1_abastecimento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import banco_dados.AbastecimentoDB;
import banco_dados.DBHelper;

public class MainActivity extends AppCompatActivity {
    TextView quilomatragemAtual;
    TextView quantidadeAbastecida;
    TextView dia;
    TextView valor;

    Button salvar;
    Button calcular;

    ListView listagem;

    List<Abastecimento> dados;

    DBHelper db;
    AbastecimentoDB abastecimentoDB;

    public void mostrarToaster(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void calcular(View view) {
        try {
            abastecimentoDB.listar(dados);
            Abastecimento primeiro = dados.get(0);
            Abastecimento ultimo = dados.get(dados.size()-1);
            System.out.println("Primeiro: " + primeiro.getDia());
            System.out.println("Ultimo: " + ultimo.getDia());
            Float quantidadeAbastecida = Float.parseFloat("0");
            for (int i = 0; i < dados.size(); i++) {
                quantidadeAbastecida = quantidadeAbastecida + dados.get(i).getQuantidadeAbastecida();
            }
            Float diferenca = ultimo.getQuilometragemAtual() - primeiro.getQuilometragemAtual();
            Float calculo = diferenca / quantidadeAbastecida;
            mostrarToaster("Calculo: " + calculo);
        } catch (Exception e) {
            System.out.println("======== Error ========");
            System.out.println(e);
            System.out.println("=======================");
        }
    }

    public void salvar(View view) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            Abastecimento abastecimento = new Abastecimento();

            abastecimento.setQuantidadeAbastecida(Float.parseFloat(quantidadeAbastecida.getText().toString()));
            abastecimento.setQuilometragemAtual(Float.parseFloat(quilomatragemAtual.getText().toString()));
            abastecimento.setDia(formatter.parse(dia.getText().toString()));
            abastecimento.setValor(Float.parseFloat(valor.getText().toString()));

            System.out.println(abastecimento.getDia());

            abastecimentoDB.inserir(abastecimento);
            mostrarToaster("Abastecimento realizado com sucesso.");

            abastecimentoDB.listar(dados);
        } catch (Exception e) {
            System.out.println("======== Error ========");
            System.out.println(e);
            System.out.println("=======================");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quilomatragemAtual = findViewById(R.id.quilometragemAtual);
        quantidadeAbastecida = findViewById(R.id.quantidadeAbastecida);
        dia = findViewById(R.id.dia);
        valor = findViewById(R.id.valor);

        salvar = findViewById(R.id.salvar);
        calcular = findViewById(R.id.calcular);

        listagem = findViewById(R.id.listagem);

        dados = new ArrayList();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listagem.setAdapter(arrayAdapter);

        db = new DBHelper(this);
        abastecimentoDB = new AbastecimentoDB(db);

        abastecimentoDB.listar(dados);

        listagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int j, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setMessage("Realmente quer remover?");
                alert.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mostrarToaster("Abastecimento deletado com sucesso.");
                        abastecimentoDB.remover(dados.get(j).getId());
                        abastecimentoDB.listar(dados);
                    }
                });
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.create().show();
                return false;
            }
        });
    }
}