package com.maikol.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DeleteActivity extends AppCompatActivity {

    private EditText editTextDeletePostId;
    private Button buttonDelete;
    private TextView textViewApiResult;

    private RequestQueue requestQueue;

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        editTextDeletePostId = findViewById(R.id.editTextDeletePostId);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewApiResult = findViewById(R.id.textViewApiResult);

        requestQueue = Volley.newRequestQueue(this);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postId = editTextDeletePostId.getText().toString().trim();

                if (postId.isEmpty()) {
                    Toast.makeText(DeleteActivity.this, "Por favor, ingrese el ID del post a eliminar", Toast.LENGTH_SHORT).show();
                } else {
                    deletePost(postId);
                }
            }
        });
    }

    private void deletePost(String postId) {
        String url = API_URL + "/" + postId;

        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textViewApiResult.setText("Post con ID " + postId + " eliminado exitosamente.");
                        Toast.makeText(DeleteActivity.this, "Post con ID " + postId + " eliminado.", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textViewApiResult.setText("Error al eliminar post con ID " + postId + ": " + error.getMessage());
                        Toast.makeText(DeleteActivity.this, "Error al eliminar post: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

        requestQueue.add(stringRequest);
    }
}