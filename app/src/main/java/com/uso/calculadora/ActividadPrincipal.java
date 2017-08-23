package com.uso.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.xml.sax.Parser;

import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {

    //Declaracion de las variables
    private Button btnAC,btnDEL,btnIGUAL;

    private TextView lblEntrada,lblSalida;

    private ArrayList<Button> lstNumeros;

    private ArrayList<Button> lstOperaciones;



    private Boolean opActiva = true;
//para que no pueda poner una op al inicio


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        //inicializacion

        btnAC   = (Button) findViewById(R.id.btnAC);

        btnDEL  = (Button) findViewById(R.id.btnDEL);

        btnIGUAL= (Button) findViewById(R.id.btnIGUAL);


        lblEntrada = (TextView) findViewById(R.id.lblEntrada);

        lblSalida  = (TextView) findViewById(R.id.lblSalida);

        //inicializando listas

        lstNumeros = new ArrayList<>();

        lstNumeros.add( (Button) findViewById(R.id.btn9));

        lstNumeros.add( (Button) findViewById(R.id.btn8));

        lstNumeros.add( (Button) findViewById(R.id.btn7));

        lstNumeros.add( (Button) findViewById(R.id.btn6));

        lstNumeros.add( (Button) findViewById(R.id.btn5));

        lstNumeros.add( (Button) findViewById(R.id.btn4));

        lstNumeros.add( (Button) findViewById(R.id.btn3));

        lstNumeros.add( (Button) findViewById(R.id.btn2));

        lstNumeros.add( (Button) findViewById(R.id.btn1));

        lstNumeros.add( (Button) findViewById(R.id.btnp));

        lstOperaciones = new ArrayList<>();

        lstOperaciones.add( (Button) findViewById(R.id.btnSUM));

        lstOperaciones.add( (Button) findViewById(R.id.btnRESTA));

        lstOperaciones.add( (Button) findViewById(R.id.btnMULTI));

        lstOperaciones.add( (Button) findViewById(R.id.btnENTRE));


        //agregar evento onClick

        btnAC.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //cuando ocurra el evento

                lblEntrada.setText("");

                lblSalida.setText("");

                opActiva = true;

            }



        });
        btnDEL.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //boton DEL

                eliminarAlFinal();

            }

        });

        btnIGUAL.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //boton IGUAL

                lblSalida.setText(Parse.evaluar(lblEntrada.getText().toString()) );

            }

        });

        //llamar funciones

        initNumeros();

        initOperaciones();

    }
//termina onCreate

    private void initNumeros(){

        //acceder a cada uno de los

        // botones en la lista

        for (final Button btn:lstNumeros){

            btn.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    String txt = lblEntrada.getText().toString();

                    txt+= btn.getText().toString();

                    lblEntrada.setText(txt);

                    opActiva=false;

                }

            });

        }

    }


    private void initOperaciones(){

        for (final Button btn:lstOperaciones){

            btn.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {


                    String txt = lblEntrada.getText().toString();

                    txt+= btn.getText().toString();

                    if(opActiva==false){

                        lblEntrada.setText(txt);

                        opActiva = true;

                    }


                }

            });

        }

    }


    private void eliminarAlFinal(){

        String entrada = lblEntrada.getText().toString();

        if(entrada.length()>0 ){

            //elimino el ultimo

            entrada = entrada.substring(0,entrada.length()-1);

            if(entrada.length()>0)

                opActiva = esOperacion(entrada.substring(0,entrada.length()-1));

            else

                opActiva = true;

        }

        lblEntrada.setText(entrada);

    }



    private boolean esOperacion(String txt){

        for (Button btn:lstOperaciones){

            //comparo que el texto que envio sea un texto de operacion

            if(btn.getText().toString().equals(txt)==true){

                return true;

            }

        }

        return false;

    }
}
