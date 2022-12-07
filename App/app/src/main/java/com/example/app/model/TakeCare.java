package com.example.app.model;

public class TakeCare {
    private String cpfCuidador;
    private String cpfIdoso;

    public TakeCare(String cpfCuidador, String cpfIdoso) {
        this.cpfCuidador = cpfCuidador;
        this.cpfIdoso = cpfIdoso;
    }

    public String getCpfCuidador() {
        return cpfCuidador;
    }

    public void setCpfCuidador(String cpfCuidador) {
        this.cpfCuidador = cpfCuidador;
    }

    public String getCpfIdoso() {
        return cpfIdoso;
    }

    public void setCpfIdoso(String cpfIdoso) {
        this.cpfIdoso = cpfIdoso;
    }
}
