package kr.ac.duksung.pongle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // 데이터베이스 이름 및 버전 설정
    private static final String DATABASE_NAME = "DSCafeteria.db";
    private static final int DATABASE_VERSION = 1;

    // 생성자
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 데이터베이스가 처음 생성될 때 호출되는 메서드
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SQL = "CREATE TABLE \"Menu\" (\n" +
                "\t\"menuID\"\tINTEGER,\n" +
                "\t\"RestID\"\tINTEGER,\n" +
                "\t\"menuName\"\tINTEGER,\n" +
                "\t\"menuPrice\"\tINTEGER,\n" +
                "\t\"soldOut\"\tTEXT DEFAULT 'NO',\n" +
                "\tPRIMARY KEY(\"menuID\"),\n" +
                "\tFOREIGN KEY(\"RestID\") REFERENCES \"Restaurant\"(\"RestID\")\n" +
                ");\n" +
                "CREATE TABLE \"Order\" (\n" +
                "\t\"orderID\"\tINTEGER,\n" +
                "\t\"stdID\"\tINTEGER,\n" +
                "\t\"seatNum\"\tINTEGER,\n" +
                "\t\"orderDate\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"orderID\"),\n" +
                "\tFOREIGN KEY(\"seatNum\") REFERENCES \"Seat\"(\"seatNum\"),\n" +
                "\tFOREIGN KEY(\"stdID\") REFERENCES \"Student\"(\"stdID\")\n" +
                ");\n" +
                "CREATE TABLE \"OrderDetail\" (\n" +
                "\t\"orderDetailID\"\tINTEGER,\n" +
                "\t\"orderID\"\tINTEGER,\n" +
                "\t\"menuID\"\tINTEGER,\n" +
                "\t\"quantity\"\tINTEGER,\n" +
                "\t\"orderStatus\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"orderDetailID\"),\n" +
                "\tFOREIGN KEY(\"orderID\") REFERENCES \"Order\"(\"orderID\"),\n" +
                "\tFOREIGN KEY(\"menuID\") REFERENCES \"Menu\"(\"menuID\")\n" +
                ");\n" +
                "CREATE TABLE \"Restaurant\" (\n" +
                "\t\"RestID\"\tINTEGER,\n" +
                "\t\"RestName\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"RestID\")\n" +
                ");\n" +
                "CREATE TABLE \"Seat\" (\n" +
                "\t\"seatID\"\tINTEGER,\n" +
                "\t\"seatName\"\tTEXT,\n" +
                "\t\"use\" TEXT,\n" +
                "\tPRIMARY KEY(\"seatID\")\n" +
                ");\n" +
                "CREATE TABLE \"Student\" (\n" +
                "\t\"stdID\"\tINTEGER NOT NULL,\n" +
                "\t\"stdName\"\tTEXT,\n" +
                "\t\"meal\"\tINTEGER,\n" +
                "\t\"pay\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"stdID\")\n" +
                ");";
        db.execSQL(CREATE_TABLE_SQL);
    }

    // 데이터베이스 버전이 변경될 때 호출되는 메서드
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS my_table");
        onCreate(db);
    }
}
