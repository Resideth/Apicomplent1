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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PutActivity extends AppCompatActivity {

    private EditText editTextPostId;
    private EditText editTextNewTitle;
    private EditText editTextNewBody;
    private Button buttonUpdate;
    private TextView textViewApiResult;

    private RequestQueue requestQueue;

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

        editTextPostId = findViewById(R.id.editTextPostId);
        editTextNewTitle = findViewById(R.id.editTextNewTitle);
        editTextNewBody = findViewById(R.id.editTextNewBody);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        textViewApiResult = findViewById(R.id.textViewApiResult);

        requestQueue = Volley.newRequestQueue(this);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postId = editTextPostId.getText().toString().trim();
                String newTitle = editTextNewTitle.getText().toString().trim();
                String newBody = editTextNewBody.getText().toString().trim();

                if (postId.isEmpty() || newTitle.isEmpty() || newBody.isEmpty()) {
                    Toast.makeText(PutActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    updatePost(postId, newTitle, newBody);
                }
            }
        });
    }

    private void updatePost(String postId, String newTitle, String newBody) {
        JSONObject postData = new JSONObject();
        try {
            // CORRECCIÓN: Se convierte el String postId a un Integer
            postData.put("id", Integer.parseInt(postId));
            postData.put("title", newTitle);
            postData.put("body", newBody);
            postData.put("userId", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = API_URL + "/" + postId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String responseId = response.getString("id");
                            String responseTitle = response.getString("title");
                            String responseBody = response.getString("body");

                            String result = "Post actualizado exitosamente:\n" +
                                    "ID: " + responseId + "\n" +
                                    "Título: " + responseTitle + "\n" +
                                    "Cuerpo: " + responseBody;
                            textViewApiResult.setText(result);
                            Toast.makeText(PutActivity.this, "Post actualizado con ID: " + responseId, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PutActivity.this, "Error al parsear la respuesta JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PutActivity.this, "Error al actualizar post: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        textViewApiResult.setText("Error al conectar con la API: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }
}