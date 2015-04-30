package sample;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "receipts")
public class Receipt {
    @DatabaseField(id = true)
    String title;

    @DatabaseField
    String text;

    @DatabaseField
    String url;

    public Receipt() {}

    public Receipt(String title, String text, String url) {
        this.title = title;
        this.text  = text;
        this.url   = url;
    }
}