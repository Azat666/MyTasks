package com.example.student.mytasks.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.student.mytasks.R;
import com.example.student.mytasks.activity.TasksListActivity;
import com.example.student.mytasks.models.Accounts;
import com.example.student.mytasks.provaider.UserProvaider;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        final Button register = view.findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new RegistorFragment()).commit();
            }
        });

        final Button button = view.findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount(view);
            }
        });
        return view;
    }


    private void openAccount(final View view) {
        EditText editName = view.findViewById(R.id.login_name1);
        EditText editEmail = view.findViewById(R.id.login_email1);
        EditText editPassword = view.findViewById(R.id.login_password1);
        final String name = editName.getText().toString();
        final String email = editEmail.getText().toString();
        final String password = editPassword.getText().toString();

        for (int i = 0; i < UserProvaider.getList().size(); i++) {
            Accounts accounts = UserProvaider.getList().get(i);
            if (name.equals(accounts.getName()) && email.equals(accounts.getEmail()) &&
                    password.equals(accounts.getPassword())) {
                Intent intent = new Intent(getActivity(), TasksListActivity.class);
                getActivity().startActivity(intent);
                return;
            } else {
                Toast.makeText(getContext(), R.string.tost_login, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
