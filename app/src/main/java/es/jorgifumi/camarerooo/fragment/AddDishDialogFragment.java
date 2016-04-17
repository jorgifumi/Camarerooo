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
import android.widget.ImageView;
import android.widget.TextView;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.model.Dish;

/**
 * Created by jorgifumi on 17/04/16.
 */
public class AddDishDialogFragment extends DialogFragment {
    OnAddDishDialogFragmentListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return createAddDishDialog();
    }

    public static AddDishDialogFragment newInstance(Bundle arguments) {
        
        AddDishDialogFragment fragment = new AddDishDialogFragment();
        if (arguments != null) {
            fragment.setArguments(arguments);
        }

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (OnAddDishDialogFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " no implementó OnAddDishDialogFragmentListener");
        }
    }

    private Dialog createAddDishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.add_dish_dialog_fragment, null);

        builder.setView(v);

        final Dish selectedDish = (Dish) getArguments().getSerializable("Dish");

        Button addDish = (Button) v.findViewById(R.id.dish_add_button);
        Button cancel = (Button) v.findViewById(R.id.dish_cancel_button);

        ImageView image = (ImageView) v.findViewById(R.id.dish_image);
        // TODO: Download and assign image
        TextView title = (TextView) v.findViewById(R.id.dish_title_text);
        title.setText(selectedDish.getName());
        TextView description = (TextView) v.findViewById(R.id.dish_description_text);
        description.setText(selectedDish.getDescription());

        ImageView crustacean = (ImageView) v.findViewById(R.id.crustacean_icon);
        if (selectedDish.getAllergens().contains("CRUSTACEAN")) { crustacean.setImageAlpha(100); } else { crustacean.setImageAlpha(50); }
        final ImageView egg = (ImageView) v.findViewById(R.id.egg_icon);
        if (selectedDish.getAllergens().contains("EGG")) { egg.setImageAlpha(100); } else { egg.setImageAlpha(50); }
        ImageView fish = (ImageView) v.findViewById(R.id.fish_icon);
        if (selectedDish.getAllergens().contains("FISH")) { fish.setImageAlpha(100); } else { fish.setImageAlpha(50); }
        ImageView milk = (ImageView) v.findViewById(R.id.milk_icon);
        if (selectedDish.getAllergens().contains("MILK")) { milk.setImageAlpha(100); } else { milk.setImageAlpha(50); }
        ImageView peanut = (ImageView) v.findViewById(R.id.peanut_icon);
        if (selectedDish.getAllergens().contains("PEANUT")) { peanut.setImageAlpha(100); } else { peanut.setImageAlpha(50); }
        ImageView soya = (ImageView) v.findViewById(R.id.soya_icon);
        if (selectedDish.getAllergens().contains("SOYA")) { soya.setImageAlpha(100); } else { soya.setImageAlpha(50); }
        ImageView wheat = (ImageView) v.findViewById(R.id.wheat_icon);
        if (selectedDish.getAllergens().contains("WHEAT")) { wheat.setImageAlpha(100); } else { wheat.setImageAlpha(50); }

        final EditText notes = (EditText) v.findViewById(R.id.dish_notes_input);

        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dish newOrder = selectedDish;
                newOrder.setNotes(notes.getText().toString());
                mListener.onAddDishButtonClick(newOrder);
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelButtonClick();
                dismiss();
            }
        });

        return builder.create();

    }

    public interface OnAddDishDialogFragmentListener {
        void onAddDishButtonClick(Dish newDish);
        void onCancelButtonClick();
    }
}
