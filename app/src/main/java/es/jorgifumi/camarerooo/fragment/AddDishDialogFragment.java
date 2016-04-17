package es.jorgifumi.camarerooo.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.model.Dish;

/**
 * Created by jorgifumi on 17/04/16.
 */
public class AddDishDialogFragment extends DialogFragment {
    private static final String TAG = "AddDishDialogFragment";
    OnAddDishDialogFragmentListener mListener;
    private Bitmap mBitmap;
    private ImageView mImage;

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

        mImage = (ImageView) v.findViewById(R.id.dish_image);
        new LoadImage().execute(selectedDish.getImageURL());
        TextView title = (TextView) v.findViewById(R.id.dish_title_text);
        title.setText(selectedDish.getName());
        TextView description = (TextView) v.findViewById(R.id.dish_description_text);
        description.setText(selectedDish.getDescription());
        Log.v(TAG, selectedDish.getAllergens().toString());

        ImageView crustacean = (ImageView) v.findViewById(R.id.crustacean_icon);
        if (selectedDish.getAllergens().contains("CRUSTACEAN")) { crustacean.setImageAlpha(255); } else { crustacean.setImageAlpha(25); }
        final ImageView egg = (ImageView) v.findViewById(R.id.egg_icon);
        if (selectedDish.getAllergens().contains("EGG")) { egg.setImageAlpha(255); } else { egg.setImageAlpha(25); }
        ImageView fish = (ImageView) v.findViewById(R.id.fish_icon);
        if (selectedDish.getAllergens().toString().contains("FISH")) { fish.setImageAlpha(255); } else { fish.setImageAlpha(25); }
        ImageView milk = (ImageView) v.findViewById(R.id.milk_icon);
        if (selectedDish.getAllergens().contains("MILK")) { milk.setImageAlpha(255); } else { milk.setImageAlpha(25); }
        ImageView peanut = (ImageView) v.findViewById(R.id.peanut_icon);
        if (selectedDish.getAllergens().contains("PEANUT")) { peanut.setImageAlpha(255); } else { peanut.setImageAlpha(25); }
        ImageView soya = (ImageView) v.findViewById(R.id.soya_icon);
        if (selectedDish.getAllergens().contains("SOYA")) { soya.setImageAlpha(255); } else { soya.setImageAlpha(25); }
        ImageView wheat = (ImageView) v.findViewById(R.id.wheat_icon);
        if (selectedDish.getAllergens().contains("WHEAT")) { wheat.setImageAlpha(255); } else { wheat.setImageAlpha(25); }

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

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
        protected Bitmap doInBackground(String... args) {
            try {
                mBitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return mBitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                mImage.setImageBitmap(image);

            }else{

                //Snackbar.make(findViewById(android.R.id.content), "Image Does Not exist or Network Error", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
