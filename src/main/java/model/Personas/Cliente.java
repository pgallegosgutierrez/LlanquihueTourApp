package model.Personas;

    public class Cliente  extends Persona{

        private String nacionalidad;

        public Cliente(String nombre, String rut, String correo, String telefono, String nacionalidad) {
            super(nombre, rut, correo, telefono);
            this.nacionalidad=nacionalidad;
        }

        public String getNacionalidad() {
            return nacionalidad;
        }

        public void setNacionalidad(String nacionalidad) {
            this.nacionalidad = nacionalidad;
        }

        @Override
        public void mostrarResumen() {
            System.out.println("Nombre:"+ nombre);
            System.out.println("Rut:"+ rut);
            System.out.println("Correo:"+ correo);
            System.out.println("Telefono:"+ telefono);
            System.out.println("Nacionalidad: "+nacionalidad);
        }
    }

