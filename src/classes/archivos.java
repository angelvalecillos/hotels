package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class archivos {
    
    public String escribir(String archivo,String low)throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            this.visible(archivo);
            String direccion=getPath(System.getProperty("user.dir"));
            archivo=direccion+"\\"+archivo;
            fichero = new FileWriter(archivo,true);
            pw = new PrintWriter(fichero);

                pw.println(low);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        this.oculto(archivo);
        return null;
    }
     public String leer(String archivo,String split)throws IOException {
            File dato = null;
            FileReader fr = null;
            BufferedReader br = null;
            String linea,t = "";
            this.visible(archivo);
      try {
          String direccion=getPath(System.getProperty("user.dir"));
          archivo=direccion+"\\"+archivo;
            
         dato = new File (archivo);
         fr = new FileReader (dato);
         br = new BufferedReader(fr);
         while((linea=br.readLine())!=null) {
             t = t+linea+split;
          }
         }      
      catch(Exception e){
         e.printStackTrace();
      }

      finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      this.oculto(archivo);
      return t;
    }
     public void nuevo(String archivo)throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            String direccion=getPath(System.getProperty("user.dir"));
            archivo=direccion+"\\"+archivo;
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);

           

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        this.oculto(archivo);
    }
     public void oculto(String file){
	try
        {
            String direccion=getPath(System.getProperty("user.dir"));
            file=direccion+"\\"+file;
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("attrib +H +R +S +A "+file +" //s");
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
    }
     public boolean prueba(String file) {
         this.visible(file);
         String direccion=getPath(System.getProperty("user.dir"));
         File folder = new File(direccion+"\\"+file);
         if(!folder.exists()){
             this.oculto(file);
            return folder.mkdirs();
         }
         else{
             this.oculto(file);
            return true;
         }
     }
     public String[] nombreArchivo(String file) {
         
         String direccion=getPath(System.getProperty("user.dir"));
         File folder = new File(direccion+file);
         File[] listOfFiles = folder.listFiles();
         String[] data =new String[listOfFiles.length];
         for (int i = 0; i < listOfFiles.length; i++) {
             if (listOfFiles[i].isFile()) {
                 data[i]=("File " + listOfFiles[i].getName());
             } else if (listOfFiles[i].isDirectory()) {
                 data[i]=("Directory " + listOfFiles[i].getName());
             }
         }
         return data;
     }
     public void eliminar(String file) {
         String direccion=getPath(System.getProperty("user.dir"));
         try{
                //Delete if tempFile exists
                File fileTemp = new File(direccion+"\\"+file);
                if (fileTemp.exists()){
                        fileTemp.delete();
                    }   
             }catch(Exception e){
            // if any error occurs
            e.printStackTrace();
            }
     }
    public void visible(String file){
	try
        {
            String direccion=getPath(System.getProperty("user.dir"));
            file=direccion+"\\"+file;
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("attrib -H -R -S -A "+file +" //s");
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
    }
    public static String getPath(String p) {
        String dir="";
        char d=((char)92);
        for(int x=0;x<p.length();x++)
        {
            char l=p.charAt(x);
            if(d==l)
            {
                dir+="\\";
            }
            else
            {
                dir+=l+"";
            }
        }
        return dir;
    }
    
}
