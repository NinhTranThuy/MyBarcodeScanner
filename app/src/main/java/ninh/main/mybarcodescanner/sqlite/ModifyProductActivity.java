package ninh.main.mybarcodescanner.sqlite;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import org.w3c.dom.Text;

import ninh.main.mybarcodescanner.R;

public class ModifyProductActivity extends Activity implements OnClickListener{
    private TextView seriText;
    private EditText nameProductText;
    private Button saveBtn;
    private ImageButton deleteBtn;
    private EditText quantityText;
    ImageButton remove,add;
    private DBManager dbManager;
    private DatabaseHelper database;
    private SQLiteDatabase sqLiteDatabase;
    String seri;
    String name;
    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product);
        setTitle("Modify Record");
        dbManager = new DBManager(this);
        dbManager.open();
        init();


        Intent intent = getIntent();
        seri = intent.getStringExtra(DatabaseHelper.Seri);
        seriText.setText(seri + " ");


        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }
    private void init(){
        seriText = findViewById(R.id.seri_edittext);
        nameProductText =  findViewById(R.id.nameProduct_edittext);
        quantityText = findViewById(R.id.quantity_edittext);
        saveBtn = (Button) findViewById(R.id.btn_save);
        add = findViewById(R.id.btnIncrease);
        remove = findViewById(R.id.btnDecrease);
        quantityText = findViewById(R.id.quantity_edittext);
        deleteBtn = (ImageButton) findViewById(R.id.btn_delete);
    }
    public void getData(){
        String selection =  DatabaseHelper.Seri + " = ? ";
        String[] selectionArgs = {seri+ " "};
        Cursor data = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME,null,selection,selectionArgs,null,null,null);
        name = data.getString(1);
        quantity = data.getInt(2);

        nameProductText.setText(name);
        quantityText.setText(quantity);
    }

    public void removeQuantity(View view) {
        if (quantity == 0){
            remove.setEnabled(false);
        } else {
            remove.setEnabled(true);
            quantity -= 5;
            quantityText.setText(quantity+"");
        }
    }

    public void addQuantity(View view) {
        quantity +=5;
        quantityText.setText(quantity+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_save):
//                Long _seri = Long.parseLong((String.valueOf(seri)));
                String nameProduct = nameProductText.getText().toString();
//                Integer quantity = Integer.parseInt(String.valueOf(_quantity));

                if (dbManager.update(seri, nameProduct, quantity) == 1){
                    Toast.makeText(this, "Save Successfully", Toast.LENGTH_SHORT).show();
                }
                this.returnHome();
                break;

            case (R.id.btn_delete):
                dbManager.delete(seri);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ProductListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
