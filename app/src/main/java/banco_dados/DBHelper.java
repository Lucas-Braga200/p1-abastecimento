package banco_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context content) {
        super(content, "BancoAbastecimento", null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table abastecimento(" +
                        "id integer primary key autoincrement," +
                        "quilometragem_atual real," +
                        "quilometragem_abastecida real," +
                        "dia Date," +
                        "valor real);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
                "create table abastecimento(" +
                        "id integer primary key autoincrement," +
                        "quilometragem_atual real," +
                        "quilometragem_abastecida real," +
                        "dia Date," +
                        "valor real);"
        );
    }
}
