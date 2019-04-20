package rs.edu.raf.rma.pvukovic16_projekat1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rs.edu.raf.rma.pvukovic16_projekat1.R;
import rs.edu.raf.rma.pvukovic16_projekat1.adapter.CategoryAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.adapter.ExpenseAdapter;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Category;
import rs.edu.raf.rma.pvukovic16_projekat1.model.Expense;
import rs.edu.raf.rma.pvukovic16_projekat1.viewmodel.MainViewModel;

public class Tab2 extends Fragment {

    private MainViewModel mainViewModel;
    private ExpenseAdapter expenseAdapter;
    private List<Expense> expenseList;


    public static Tab2 newInstance() {
        Tab2 fragment = new Tab2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment2, container, false);

        EditText searchText = root.findViewById(R.id.t2_et_search);
        Spinner spinner = root.findViewById(R.id.t2_spinner);
        Button apply = root.findViewById(R.id.t2_btn_apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category cat = (Category)spinner.getSelectedItem();
                if (cat != null) {

                }
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.t2_rv_exp_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        expenseAdapter = new ExpenseAdapter();

        recyclerView.setAdapter(expenseAdapter);

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getExpenses().observe(getViewLifecycleOwner(),
                new Observer<List<Expense>>() {
                    @Override
                    public void onChanged(List<Expense> expenses) {
                        expenseList = new ArrayList<>(expenses);
                        expenseAdapter.setData(expenseList);
                    }
                });
    }

}
