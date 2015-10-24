package pt.afonso.joao.mybmicalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static pt.afonso.joao.mybmicalculator.R.string.Kilograms;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner sex_spinner = (Spinner) findViewById(R.id.sex);
        Spinner height_spinner = (Spinner) findViewById(R.id.height_spinner);
        Spinner weight_spinner = (Spinner) findViewById(R.id.weight_spinner);

        ArrayAdapter<CharSequence> sex_adapter = ArrayAdapter.createFromResource(this, R.array.sex_units, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> height_adapter = ArrayAdapter.createFromResource(this, R.array.height_units, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> weight_adapter = ArrayAdapter.createFromResource(this, R.array.weight_units, android.R.layout.simple_spinner_item);

        sex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        height_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sex_spinner.setAdapter(sex_adapter);
        height_spinner.setAdapter(height_adapter);
        weight_spinner.setAdapter(weight_adapter);

    }

    public void calculateBMI(View v){

        EditText height_txt = (EditText) findViewById(R.id.height);
        EditText weight_txt = (EditText) findViewById(R.id.weight);

        Spinner height_spin = (Spinner) findViewById(R.id.height_spinner);
        Spinner weight_spin = (Spinner) findViewById(R.id.weight_spinner);
        Spinner sex_spin = (Spinner) findViewById(R.id.sex);

        if (height_txt == null || weight_txt == null || weight_txt.length() < 1 || height_txt.length() < 1){
            return ;
        }

        double height_val = Double.valueOf(height_txt.getText().toString());
        double weight_val = Double.valueOf(weight_txt.getText().toString());

        String height_unit = (String) height_spin.getSelectedItem().toString();
        String weight_unit = (String) weight_spin.getSelectedItem().toString();
        String sex_unit = (String) sex_spin.getSelectedItem().toString();


        ArrayAdapter<CharSequence> height_arr = ArrayAdapter.createFromResource(this, R.array.height_units, R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> weight_arr = ArrayAdapter.createFromResource(this, R.array.weight_units, R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> sex_arr = ArrayAdapter.createFromResource(this, R.array.sex_units, R.layout.support_simple_spinner_dropdown_item);


       //if not metric
        if (weight_unit.compareTo(this.getString(R.string.Kilograms)) != 0){
            weight_val = weight_val / 2.2046;
        }

        //if not metric
        if (height_unit.compareTo(this.getString(R.string.Centimeters).toString()) != 0){
            height_val = height_val / 0.032808;
        }

        double bmi_val = weight_val / ((height_val/100)*(height_val/100));

        System.out.println("Height_val: "+height_val+" Weight_val: "+weight_val+" bmi_val "+bmi_val+" "+this.getString(R.string.Centimeters).toString()+" "+this.getString(R.string.Kilograms));

        TextView result = (TextView) this.findViewById(R.id.result);

        result.setText(String.valueOf(bmi_val));
        result.setVisibility(View.VISIBLE);
    }
}
