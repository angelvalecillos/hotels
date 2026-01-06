package classes;

public class createTables {

    public String creater(String tableOrAction) {
        String statement = "";
        
        System.out.println(tableOrAction);
        ManejadorSql sql = new ManejadorSql(null, null, null, null, null, null, null, null, null);

        switch (tableOrAction) {
            // 1) cargoshab
            case "cargoshab" -> statement =
                "CREATE TABLE IF NOT EXISTS cargoshab (" +
                "  id SERIAL PRIMARY KEY, " +
                "  huesped character varying, " +
                "  puni character varying, " +
                "  formapago character varying, " +
                "  dep character varying, " +
                "  consumo character varying, " +
                "  tarjeta character varying, " +
                "  empleado character varying, " +
                "  serv character varying, " +
                "  pago character varying, " +
                "  numero character varying, " +
                "  fecha date, " +
                "  cantidad numeric" +
                ");";

            // 2) departamento
            case "departamento" -> statement =
                "CREATE TABLE IF NOT EXISTS departamento (" +
                "  id SERIAL PRIMARY KEY, " +
                "  descripcion text, " +
                "  nombre character varying(30)" +
                ");";

            // 3) empleado
            case "empleado" -> statement =
                "CREATE TABLE IF NOT EXISTS empleado (" +
                "  id SERIAL PRIMARY KEY, " +
                "  departamento integer NOT NULL, " +
                "  cargo character varying(100) NOT NULL, " +
                "  condicion character varying(50), " +
                "  link text, " +
                "  f_egreso date, " +
                "  profesion integer NOT NULL, " +
                "  persona character varying(20) NOT NULL, " +
                "  salario numeric(12,2), " +
                "  f_ingreso date NOT NULL, " +
                "  data bytea" +
                ");";

            // 4) habitacion
            case "habitacion" -> statement =
                "CREATE TABLE IF NOT EXISTS habitacion (" +
                "  id SERIAL PRIMARY KEY, " +
                "  tipo integer NOT NULL, " +
                "  observaciones character varying(250), " +
                "  piso character varying(20), " +
                "  numero character varying(20)" +
                ");";

            // 5) huesped
            case "huesped" -> statement =
                "CREATE TABLE IF NOT EXISTS huesped (" +
                "  idguest SERIAL PRIMARY KEY, " +
                "  vehiculo character varying(30), " +
                "  fecha_in date, " +
                "  fecha_out date, " +
                "  hora_out character varying(20), " +
                "  pais integer NOT NULL, " +
                "  condicion text, " +
                "  calidad character varying(30), " +
                "  hora_in character varying(20), " +
                "  observacion text, " +
                "  hab integer NOT NULL, " +
                "  id_tarifario integer, " +
                "  cantadulto smallint, " +
                "  cantmenor smallint" +
                ");";

            // 6) huesped_add
            case "huesped_add" -> statement =
                "CREATE TABLE IF NOT EXISTS huesped_add (" +
                "  id SERIAL PRIMARY KEY, " +
                "  huesped integer NOT NULL, " +
                "  persona character varying(20), " +
                "  con character varying" +
                ");";

            // 7) imagen
            case "imagen" -> statement =
                "CREATE TABLE IF NOT EXISTS imagen (" +
                "  id SERIAL PRIMARY KEY, " +
                "  nombre character varying(30), " +
                "  link character varying(255), " +
                "  imagen bytea" +
                ");";

            // 8) pais
            case "pais" -> statement =
                "CREATE TABLE IF NOT EXISTS pais (" +
                "  id SERIAL PRIMARY KEY, " +
                "  codigo character varying(10), " +
                "  nombre character varying(35)" +
                ");";

            // 9) per_tel
            case "per_tel" -> statement =
                "CREATE TABLE IF NOT EXISTS per_tel (" +
                "  id SERIAL PRIMARY KEY, " +
                "  persona character varying(20), " +
                "  tipo character varying(5), " +
                "  telefono character varying(20)" +
                ");";

            // 10) permiso
            case "permiso" -> statement =
                "CREATE TABLE IF NOT EXISTS permiso (" +
                "  id SERIAL PRIMARY KEY, " +
                "  nombre character varying(30)" +
                ");";

            // 11) persona (incluye UNIQUE en identificacion)
            case "persona" -> statement =
                "CREATE TABLE IF NOT EXISTS persona (" +
                "  id SERIAL PRIMARY KEY, " +
                "  direccion character varying(20), " +
                "  sexo character varying(20), " +
                "  p_nombre character varying(20), " +
                "  ecivil character varying(20), " +
                "  s_nombre character varying(20), " +
                "  correo character varying(30), " +
                "  fenac date, " +
                "  s_apellido character varying(20), " +
                "  identificacion character varying(20) UNIQUE, " +
                "  p_apellido character varying(20), " +
                "  pais integer" +
                ");";

            // 12) precio
            case "precio" -> statement =
                "CREATE TABLE IF NOT EXISTS precio (" +
                "  id SERIAL PRIMARY KEY, " +
                "  tipo integer NOT NULL, " +
                "  tarifa integer NOT NULL" +
                ");";

            // 13) profesion
            case "profesion" -> statement =
                "CREATE TABLE IF NOT EXISTS profesion (" +
                "  id SERIAL PRIMARY KEY, " +
                "  descripcion text, " +
                "  nombre character varying(30)" +
                ");";

            // 14) tarifario (según esquema actual que enviaste)
            case "tarifario" -> statement =
                "CREATE TABLE IF NOT EXISTS tarifario (" +
                "  id SERIAL PRIMARY KEY, " +
                "  temporada \"char\", " +
                "  rango \"char\", " +
                "  precio numeric(12,2), " +
                "  id_tiphab integer" +
                ");";

            // 15) tarjetas (BIGSERIAL)
            case "tarjetas" -> statement =
                "CREATE TABLE IF NOT EXISTS tarjetas (" +
                "  id BIGSERIAL PRIMARY KEY, " +
                "  nombre character varying" +
                ");";

            // 16) tiphab
            case "tiphab" -> statement =
                "CREATE TABLE IF NOT EXISTS tiphab (" +
                "  id SERIAL PRIMARY KEY, " +
                "  capacidad character varying(3), " +
                "  nombre character varying(20), " +
                "  tv character varying(1), " +
                "  banos character varying(1), " +
                "  cama character varying(1), " +
                "  cocina boolean, " +
                "  otros character varying(300), " +
                "  terraza boolean, " +
                "  aire character varying(1), " +
                "  imagen character varying(20)" +
                ");";

            // 17) usuario
            case "usuario" -> statement =
                "CREATE TABLE IF NOT EXISTS usuario (" +
                "  id SERIAL PRIMARY KEY, " +
                "  empleado integer, " +
                "  pregunta character varying(20), " +
                "  respuesta character varying(30), " +
                "  permiso integer NOT NULL, " +
                "  clave character varying(45), " +
                "  f_u_clave date, " +
                "  clave_ant character varying(45), " +
                "  usuario character varying(20)" +
                ");";

            // FOREIGN KEYS (creadas aparte, solo si no existen)
            case "foreign" -> statement =
                "DO $$ " +
                "BEGIN " +
                "  -- empleado -> departamento\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'empleado_departamento_fkey') THEN\n" +
                "    ALTER TABLE empleado ADD CONSTRAINT empleado_departamento_fkey FOREIGN KEY (departamento) REFERENCES departamento(id);\n" +
                "  END IF;\n" +
                "  -- empleado -> profesion\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'empleado_profesion_fkey') THEN\n" +
                "    ALTER TABLE empleado ADD CONSTRAINT empleado_profesion_fkey FOREIGN KEY (profesion) REFERENCES profesion(id);\n" +
                "  END IF;\n" +
                "  -- empleado -> persona (identificacion)\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'empleado_persona_fkey') THEN\n" +
                "    ALTER TABLE empleado ADD CONSTRAINT empleado_persona_fkey FOREIGN KEY (persona) REFERENCES persona(identificacion);\n" +
                "  END IF;\n" +
                "  -- per_tel -> persona\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'per_tel_persona_fkey') THEN\n" +
                "    ALTER TABLE per_tel ADD CONSTRAINT per_tel_persona_fkey FOREIGN KEY (persona) REFERENCES persona(identificacion);\n" +
                "  END IF;\n" +
                "  -- habitacion -> tiphab\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'habitacion_tipo_fkey') THEN\n" +
                "    ALTER TABLE habitacion ADD CONSTRAINT habitacion_tipo_fkey FOREIGN KEY (tipo) REFERENCES tiphab(id);\n" +
                "  END IF;\n" +
                "  -- huesped -> habitacion\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'huesped_hab_fkey') THEN\n" +
                "    ALTER TABLE huesped ADD CONSTRAINT huesped_hab_fkey FOREIGN KEY (hab) REFERENCES habitacion(id);\n" +
                "  END IF;\n" +
                "  -- huesped -> pais\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'huesped_pais_fkey') THEN\n" +
                "    ALTER TABLE huesped ADD CONSTRAINT huesped_pais_fkey FOREIGN KEY (pais) REFERENCES pais(id);\n" +
                "  END IF;\n" +
                "  -- huesped_add -> huesped\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'huesped_add_huesped_fkey') THEN\n" +
                "    ALTER TABLE huesped_add ADD CONSTRAINT huesped_add_huesped_fkey FOREIGN KEY (huesped) REFERENCES huesped(idguest);\n" +
                "  END IF;\n" +
                "  -- huesped_add -> persona\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'huesped_add_persona_fkey') THEN\n" +
                "    ALTER TABLE huesped_add ADD CONSTRAINT huesped_add_persona_fkey FOREIGN KEY (persona) REFERENCES persona(identificacion);\n" +
                "  END IF;\n" +
                "  -- precio -> tarifario\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'precio_tarifa_fkey') THEN\n" +
                "    ALTER TABLE precio ADD CONSTRAINT precio_tarifa_fkey FOREIGN KEY (tarifa) REFERENCES tarifario(id);\n" +
                "  END IF;\n" +
                "  -- precio -> tiphab\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'precio_tipo_fkey') THEN\n" +
                "    ALTER TABLE precio ADD CONSTRAINT precio_tipo_fkey FOREIGN KEY (tipo) REFERENCES tiphab(id);\n" +
                "  END IF;\n" +
                "  -- usuario -> empleado\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'usuario_empleado_fkey') THEN\n" +
                "    ALTER TABLE usuario ADD CONSTRAINT usuario_empleado_fkey FOREIGN KEY (empleado) REFERENCES empleado(id);\n" +
                "  END IF;\n" +
                "  -- usuario -> permiso\n" +
                "  IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'usuario_permiso_fkey') THEN\n" +
                "    ALTER TABLE usuario ADD CONSTRAINT usuario_permiso_fkey FOREIGN KEY (permiso) REFERENCES permiso(id);\n" +
                "  END IF;\n" +
                "END $$;";

            // Índices adicionales (no ligados a PK/UNIQUE)
            case "indexes" -> statement =
                "CREATE INDEX IF NOT EXISTS idx_cargoshab_cantidad ON cargoshab (cantidad);" +
                "CREATE INDEX IF NOT EXISTS idx_cargoshab_fecha ON cargoshab (fecha);";

            default -> {
                System.out.println("Tabla/acción no reconocida: " + tableOrAction);
                statement = "";
            }
        }

        if (!statement.isEmpty()) {
            sql.SqlCreateTables(statement);
        }
        return statement;
    }
}