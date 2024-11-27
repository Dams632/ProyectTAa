package org.proyecto.Config;

import java.io.FileInputStream;
import java.util.Properties;

import static java.lang.Integer.parseInt;

public class LeerConfig {
    private Properties properties;



    public LeerConfig(String configFile){
        properties=new Properties();
        try(FileInputStream input = new FileInputStream(configFile)){
                properties.load(input);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    public int getPort(){
        return Integer.parseInt(properties.getProperty("server.port"));
    }
    public int getMaxConexiones(){
        return  Integer.parseInt(properties.getProperty("conexiones.max"));
    }
    public int getMinConexiones(){
        return Integer.parseInt(properties.getProperty("conexiones.min"));
    }
    public int getTimeOut(){
        return Integer.parseInt(properties.getProperty("time.timeOut"));
    }
    public String getIp(){
        return properties.getProperty("server.ip");
    }
}
