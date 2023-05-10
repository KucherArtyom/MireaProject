package ru.mirea.kucheras.mireaproject;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import ru.mirea.kucheras.mireaproject.databinding.FragmentFilesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilesFragment extends Fragment {
    private FragmentFilesBinding binding;
    private String fileName = "mirea.txt";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FilesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FilesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FilesFragment newInstance(String param1, String param2) {
        FilesFragment fragment = new FilesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilesBinding.inflate(inflater,container,false);
        EditText editFileName = binding.editFileName;
        EditText editQuote = binding.editQuote;
        Button buttonconvrert = binding.buttonconvrert;

        buttonconvrert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quote = editQuote.getText().toString();
                quote = Code(quote);
                editFileName.setText(quote);
                Toast.makeText(getActivity(), "Цитата закодирована!",Toast.LENGTH_LONG).show();
                FileOutputStream outputStream;
                try {
                    outputStream = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                    outputStream.write(quote.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        // Inflate the layout for this fragment
        return binding.getRoot();



    }
    private String Code(String s){
        String ss ="";
        for (int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            int k = (int) c;
            k = k^20;
            c = (char) k;
            ss +=c ;
        }
        return ss;
    }
}