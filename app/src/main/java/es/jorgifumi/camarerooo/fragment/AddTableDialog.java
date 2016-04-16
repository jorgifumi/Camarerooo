package es.jorgifumi.camarerooo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.model.Table;

/**
 * Created by jorgifumi on 15/04/16.
 */
public class AddTableDialog extends DialogFragment {
    OnAddTableDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createAddTableDialog();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnAddTableDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " no implementó OnSimpleDialogListener");
        }
    }

    private Dialog createAddTableDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.add_table, null);

        builder.setView(v);

        Button addTable = (Button) v.findViewById(R.id.button_add_table);
        Button cancel = (Button) v.findViewById(R.id.button_cancel);

        final EditText tableName = (EditText) v.findViewById(R.id.nombre_mesa_input);
        final EditText numDiners = (EditText) v.findViewById(R.id.num_comensales_input);

        addTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Table newTable = new Table(tableName.getText().toString(), Integer.parseInt(numDiners.getText().toString()));
                listener.onAddTableButtonClick(newTable);
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancelButtonClick();
                dismiss();
            }
        });

        return builder.create();
    }

    public interface OnAddTableDialogListener {
        void onAddTableButtonClick(Table newTable);
        void onCancelButtonClick();
    }

}
