package banco_dados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.unigran.p1_abastecimento.Abastecimento;

public class AbastecimentoDB {
    DBHelper db;

    private SQLiteDatabase conexao;

    public AbastecimentoDB(DBHelper db) {
        this.db = db;
    }

    public void inserir(Abastecimento abastecimento) {
        conexao = db.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("quilometragem_atual", abastecimento.getQuilometragemAtual());
        valores.put("quantidade_abastecida", abastecimento.getQuantidadeAbastecida());
        valores.put("dia", abastecimento.getDia().toString());
        valores.put("valor", abastecimento.getValor());
        conexao.insertOrThrow("abastecimento", null, valores);
        conexao.close();
    }

    public void listar(List dados) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            dados.clear();
            conexao = db.getReadableDatabase();
            String names[] ={"id", "quilometragem_atual", "quantidade_abastecida", "dia", "valor"};
            Cursor query = conexao.query("abastecimento", names, null, null, null, null, "dia");
            Integer i = 0;
            System.out.println("LISTAGEM");
            while (query.moveToNext()) {
                System.out.println(i);
                System.out.println(formatter.parse(query.getString(3)));
                i++;
                Abastecimento abastecimento = new Abastecimento();
                abastecimento.setId(Integer.parseInt(query.getString(0)));
                abastecimento.setQuilometragemAtual(Float.parseFloat(query.getString(1)));
                abastecimento.setQuantidadeAbastecida(Float.parseFloat(query.getString(2)));
                abastecimento.setDia(formatter.parse(query.getString(3)));
                dados.add(abastecimento);
            }
            conexao.close();
        } catch (Exception e) {
            System.out.println("======== Error ========");
            System.out.println(e);
            System.out.println("=======================");
        }
    }

    public void remover(Integer id) {
        conexao = db.getWritableDatabase();
        conexao.delete("abastecimento", "id=?", new String[]{ id+"" });
        conexao.close();
    }
}
