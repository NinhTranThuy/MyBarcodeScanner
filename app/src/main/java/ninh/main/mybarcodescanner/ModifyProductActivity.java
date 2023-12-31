package ninh.main.mybarcodescanner;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import ninh.main.mybarcodescanner.Product;
import ninh.main.mybarcodescanner.R;
import ninh.main.mybarcodescanner.sqlite.DBManager;
import ninh.main.mybarcodescanner.sqlite.DatabaseHelper;
import ninh.main.mybarcodescanner.ui.home.HomeFragment;

public class ModifyProductActivity extends Activity{
    private TextView seriText, dateText;
    private EditText nameProductText;
    private Button saveBtn;
    private ImageButton deleteBtn;
    private EditText quantityText;
    ImageButton remove,add;
    private DBManager dbManager;
    private DatabaseHelper helper;
    String seri;
    String name;
    int quantity = 0;
    String date;
    String serigui;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product);
        setTitle("Modify Record");
        dbManager = new DBManager(this);
        dbManager.open();
        helper = new DatabaseHelper(this);  // Add this line to initialize the DatabaseHelper
        init();

        Intent intent = getIntent();
        seri = intent.getStringExtra(DatabaseHelper.Seri);
        seriText.setText(seri);
        getData();


    }
    private void init(){
        seriText = findViewById(R.id.seri_edittext);
        dateText = findViewById(R.id.tvDateModify);
        nameProductText =  findViewById(R.id.nameProduct_edittext);
        quantityText = findViewById(R.id.quantity_edittext);
        saveBtn = (Button) findViewById(R.id.btn_save);
        add = findViewById(R.id.btnIncrease);
        remove = findViewById(R.id.btnDecrease);
        quantityText = findViewById(R.id.quantity_edittext);
        deleteBtn = (ImageButton) findViewById(R.id.btn_delete);
    }

    public void getData(){
        try (Cursor data = dbManager.getData(seri)) {
            if (data != null && data.moveToFirst()) {
                // Check if the Cursor has data and move to the first row
                Toast.makeText(this, "Existed", Toast.LENGTH_SHORT).show();

                // Retrieve data from the Cursor
                name = data.getString(1);
                quantity = data.getInt(2);
                date = data.getString(3);

                // Set the values to the corresponding views
                nameProductText.setText(name);
                quantityText.setText(String.valueOf(quantity));
                dateText.setText(date);

            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error retrieving data", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeQuantityModify(View view) {
        if (quantity == 0){
            remove.setEnabled(false);
        } else {
            remove.setEnabled(true);
            quantity -= 5;
            quantityText.setText(quantity+"");
        }
    }
    public void addQuantityModify(View view) {
        quantity +=5;
        quantityText.setText(quantity+"");
    }


    public void saveData(View view) {
        String nameProduct = nameProductText.getText().toString();
        long check = dbManager.update(seri, nameProduct, quantity);
        if (check != 1){
            Toast.makeText(this, "Save Successfully", Toast.LENGTH_SHORT).show();
        }
        this.returnScanModify(view);
    }

    public void deleteData(View view) {
        String seri = seriText.getText().toString();
        String name = nameProductText.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm' - 'dd.MM.yyyy");
        String date = sdf.format(new Date());

        dbManager.insert_bin(seri, name, quantity,date);

        dbManager.delete(seri);
        this.returnList(view);
    }

    public void returnScanModify(View view) {
        Intent home_intent = new Intent(getApplicationContext(), HomeFragment.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

    public void returnList(View view) {
        Intent home_intent = new Intent(getApplicationContext(), ListFragment.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
