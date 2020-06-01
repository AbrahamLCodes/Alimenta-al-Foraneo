package proyecto.iceCreamSandwich.alimenta_al_forneo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.mario.alimenta_al_forneo.R;


public class CostumeDialog extends AppCompatDialogFragment  {
    private ImageView image1,image2,image3;
    private int temp;


    private EditText edtxtname;
    private CostumeDialogInterface listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.layout_dialog, null);

        image1 = view.findViewById(R.id.skinRojo);
        image2 = view.findViewById(R.id.skinAzul);
        image3 = view.findViewById(R.id.skinNormal);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = R.drawable.foraneodos;
                Toast.makeText(getContext(), "Rojo", Toast.LENGTH_SHORT).show();
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = R.drawable.foraneodiez;
                Toast.makeText(getContext(), "Azul", Toast.LENGTH_SHORT).show();
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = R.drawable.foraneoocho;
                Toast.makeText(getContext(),"Vaquero",Toast.LENGTH_SHORT).show();
            }
        });



        builder.setView(view)
                .setTitle("Ajustes")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Name = edtxtname.getText().toString();
                        listener.apllytext(Name);
                        if(temp != 0){
                            MainActivity.setForaneo(temp);

                        }else{

                        }
                    }
                });
        edtxtname  = view.findViewById(R.id.edtxt_nameforeign);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener =(CostumeDialogInterface ) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"wut");
        }
    }

    public interface CostumeDialogInterface {
        void apllytext(String Name);
    }

}


