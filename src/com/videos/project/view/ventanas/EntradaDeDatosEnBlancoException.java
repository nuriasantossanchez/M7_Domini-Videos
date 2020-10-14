package com.videos.project.view.ventanas;

/**
 * Exception que es lanzada cuando alguno de los datos de entrada
 * esta en blanco
 *
 */
class EntradaDeDatosEnBlancoException extends Exception {

    private static final long serialVersionUID = 1L;

    public EntradaDeDatosEnBlancoException() {

    }

    public EntradaDeDatosEnBlancoException(String errorMessage) {

        super(errorMessage);
    }

}
