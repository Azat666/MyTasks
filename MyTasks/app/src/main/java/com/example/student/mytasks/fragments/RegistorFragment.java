package com.example.student.mytasks.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.student.mytasks.R;
import com.example.student.mytasks.models.Accounts;
import com.example.student.mytasks.provaider.UserProvaider;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistorFragment extends Fragment {


    public RegistorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_registor, container, false);
        creatAccount(view);
        return view;
    }


    private void creatAccount(final View view) {
        Button buttonadd = view.findViewById(R.id.reg_reg);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setRegister(view)) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new LoginFragment()).commit();
                }
            }
        });
    }

    private boolean setRegister(final View view) {
        final EditText name = view.findViewById(R.id.register_name);
        final EditText surname = view.findViewById(R.id.register_surName);
        final EditText number = view.findViewById(R.id.register_PhoneNumber);
        final EditText email = view.findViewById(R.id.register_email);
        final EditText password = view.findViewById(R.id.register_password);

        Accounts accounts = new Accounts(name.getText().toString(), email.getText().toString(), password.getText().toString());
        accounts.setSurname(surname.getText().toString());
        accounts.setNumber(number.getText().toString());
        UserProvaider.getList().add(accounts);
        return true;
    }

}
