package com.chejconsultor.chej_androidnativeradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ResponseCache;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //region Attributes

    private TextView lblTitle;
    private EditText txtValue1;
    private EditText txtValue2;
    private EditText txtResult;
    private RadioButton rbSum;
    private RadioButton rbSub;
    private Button btnCalculate;
    private Button btnClear;

    private String number1;
    private String number2;
    private String result;

    //endregion Attributes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.SetInitialData();
    }

    //region Methods

    private void SetInitialData()
    {
        this.lblTitle = (TextView)findViewById(R.id.lblTitle);
        this.txtValue1 = (EditText)findViewById(R.id.txtValue1);
        this.txtValue2 = (EditText)findViewById(R.id.txtValue2);
        this.txtResult = (EditText)findViewById(R.id.txtResult);
        this.txtResult.setEnabled(false);
        this.rbSum = (RadioButton)findViewById(R.id.rbSum);
        this.rbSub = (RadioButton)findViewById(R.id.rbSub);
        this.btnCalculate = (Button)findViewById(R.id.btnCalculate);
        this.btnClear = (Button)findViewById(R.id.btnClear);
    }

    public void GetCalcule(
            View _view)
    {
        try
        {
            if(this.rbSum.isChecked() == false && this.rbSub.isChecked() == false)
            {
                this.rbSum.setChecked(true);
            }

            this.GetValue();
            Response response = this.GetResult(
                    (this.rbSum.isChecked() == false && this.rbSub.isChecked() == false) ? 0 :
                            (this.rbSum.isChecked() ? 0 : 1));
            if(!response.IsSucces)
            {
                Toast.makeText(this, response.Message, Toast.LENGTH_SHORT).show();
            }

            this.txtResult.setText(String.valueOf(response.Result));
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void GetClear(
            View _view)
    {
        try
        {
            this.txtValue1.setText("");
            this.txtValue2.setText("");
            this.txtResult.setText("");

            Toast.makeText(this, "Clear screen...!!!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void GetValue()
    {
        this.number1 = this.txtValue1.getText().toString();
        this.number2 = this.txtValue2.getText().toString();
        this.result = this.txtResult.getText().toString();
    }

    private Response GetResult(
            int _operationType)
    {
        try
        {
            double  valorNumber1 =
                    Double.parseDouble(this.number1.length() == 0 ? "0" : this.number1);
            double  valorNumber2 =
                    Double.parseDouble(this.number2.length() == 0 ? "0" : this.number2);

            switch (_operationType)
            {
                case 0:
                    return new Response(
                            true,
                            "Method add is ok...!!!",
                            (this.SetDecimals((valorNumber1 + valorNumber2), 2)));

                case 1:
                    return new Response(
                            true,
                            "Method subtract is ok...!!!",
                            (this.SetDecimals((valorNumber1 - valorNumber2), 2)));
            }

            return new Response(
                    true,
                    "",
                    0);
        }
        catch (Exception ex)
        {
            return new Response(
                    false,
                    ex.getMessage(),
                    0);
        }
    }

    private String SetDecimals(
            double _value,
            int _decimals)
    {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(_decimals);
        return decimalFormat.format(_value);
    }

    //endregion Methods
}