package com.example.calculatorapp.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculatorapp.R;
import com.example.calculatorapp.model.CalculatorImpl;
import com.example.calculatorapp.model.Operator;
import com.example.calculatorapp.model.Theme;
import com.example.calculatorapp.model.ThemeRepository;
import com.example.calculatorapp.model.ThemeRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalculatorView {

    private TextView resultTxt;

    private CalculatorPresenter presenter;

    private ThemeRepository themeRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        themeRepository = ThemeRepositoryImpl.getInstance(this);

        setTheme(themeRepository.getSavedTheme().getThemeRes());

        setContentView(R.layout.activity_main);

        resultTxt = findViewById(R.id.result);

        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        if (savedInstanceState != null ) {

            presenter.setArgOne((Double) savedInstanceState.getSerializable("KEY_ARG1"));
            presenter.setArgTwo((Double) savedInstanceState.getSerializable("KEY_ARG2"));
            presenter.setSelectedOperator((Operator) savedInstanceState.getSerializable("KEY_OPER"));
            presenter.setLastRes((Double) savedInstanceState.getSerializable("KEY_LASTRES"));
            presenter.setDotPressed((Boolean) savedInstanceState.getSerializable("KEY_isDotPressed"));
            presenter.setDotAlreadyPressed((Boolean) savedInstanceState.getSerializable("KEY_isDotAlreadyPressed"));
            presenter.setEqualsPressed((Boolean) savedInstanceState.getSerializable("KEY_isEqualsPressed"));
            presenter.setN((Integer) savedInstanceState.getSerializable("KEY_counterN"));
            presenter.setPlusminPressed((Boolean) savedInstanceState.getSerializable("KEY_plusMin"));
            presenter.showFormatted(presenter.getLastRes());
        }else{

        }


        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);
        digits.put(R.id.key_0, 0);


        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };


        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);


        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_minus, Operator.SUB);
        operators.put(R.id.key_plus, Operator.ADD);
        operators.put(R.id.key_mult, Operator.MULT);
        operators.put(R.id.key_div, Operator.DIV);
        operators.put(R.id.key_percent, Operator.PCENT);


        View.OnClickListener operatorsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };
        try {
            findViewById(R.id.key_minus).setOnClickListener(operatorsClickListener);
            findViewById(R.id.key_plus).setOnClickListener(operatorsClickListener);
            findViewById(R.id.key_div).setOnClickListener(operatorsClickListener);
            findViewById(R.id.key_mult).setOnClickListener(operatorsClickListener);
            findViewById(R.id.key_percent).setOnClickListener(operatorsClickListener);
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed();
            }
        });


        findViewById(R.id.key_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCencelPressed();
                showResult("");
            }
        });

        findViewById(R.id.key_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEqualsPressed();
            }
        });

        findViewById(R.id.key_plusmin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onPlusMinusPressed();
            }
        });


        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK){
                Intent intent = result.getData();

                Theme selectedTheme = (Theme) intent.getSerializableExtra(SelectThemeActivity.EXTRA_THEME);

                themeRepository.saveTheme(selectedTheme);

                recreate();
            }

        });

        findViewById(R.id.theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SelectThemeActivity.class);
                intent.putExtra(SelectThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme());

                themeLauncher.launch(intent);
            }
        });
    }


    @Override
    public void showResult(String result) {
        resultTxt.setText(result);

    }

    @Override
    public void showNotice() {
        Toast.makeText(MainActivity.this, "на НОЛЬ делить нельзя", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("KEY_ARG1", presenter.getArgOne());
        outState.putSerializable("KEY_ARG2", presenter.getArgTwo());
        outState.putSerializable("KEY_OPER", presenter.getSelectedOperator());
        outState.putSerializable("KEY_LASTRES", presenter.getLastRes());
        outState.putSerializable("KEY_isDotPressed", presenter.isDotPressed());
        outState.putSerializable("KEY_isDotAlreadyPressed", presenter.isDotAlreadyPressed());
        outState.putSerializable("KEY_isEqualsPressed", presenter.isEqualsPressed());
        outState.putSerializable("KEY_counterN", presenter.getN());
        outState.putSerializable("KEY_plusMin", presenter.isPlusminPressed());

    }
}