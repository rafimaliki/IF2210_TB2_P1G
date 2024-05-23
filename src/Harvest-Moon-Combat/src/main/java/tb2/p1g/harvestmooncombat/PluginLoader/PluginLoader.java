package tb2.p1g.harvestmooncombat.PluginLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import tb2.p1g.harvestmooncombat.Models.Kartu;
import tb2.p1g.harvestmooncombat.Models.KartuProduk;
import tb2.p1g.harvestmooncombat.Models.MuatInterface;
import tb2.p1g.harvestmooncombat.Models.SimpanInterface;




public class PluginLoader {
    private List<Class<?>> plugins = new ArrayList<>();



    public void loadPlugins(String pluginDir) throws Exception {
        File dir = new File(pluginDir);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".jar"));
        if (files == null) return;
        for (File file : files) {
            loadPlugin(file);
        }
    }

    private void loadPlugin(File file) throws Exception {
        URL jarUrl = file.toURI().toURL();
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl})) {
            // Ambil nama JAR tanpa ekstensi
            String jarName = file.getName().replaceAll("\\.jar$", "");

            // Ganti titik dengan garis miring sebagai pemisah paket
            String packageName = "org.example"; // sesuaikan dengan struktur paket Anda
            String className = packageName + "." + jarName;
            Class<?> pluginClass = classLoader.loadClass(className);

            plugins.add(pluginClass);

        }
    }

    public List<Class<?>> getObjectPlugins() {
        return plugins;
    }

    public String getNamaPlugin(int idplugin) throws Exception{
        Object pluginObject = getObjectPlugins().get(idplugin).getDeclaredConstructor().newInstance();
        Method method = getObjectPlugins().get(idplugin).getMethod("getNama");
        Object result =  method.invoke(pluginObject);
        return (String)  result;
    }
    public void saveUsePlugins(int idplugin,String filepath,Object data) throws Exception{
        Object pluginObject = getObjectPlugins().get(idplugin).getDeclaredConstructor().newInstance();
        Method method = getObjectPlugins().get(idplugin).getMethod("processAndReturnJson",Object.class,String.class);
        method.invoke(pluginObject,data,filepath);
    }

    public Object loadUsePlugins(int idplugin,String filepath) throws  Exception{
        Object pluginObject = getObjectPlugins().get(idplugin).getDeclaredConstructor().newInstance();
        Method method = getObjectPlugins().get(idplugin).getMethod("deserialize",String.class);
        Object result = method.invoke(pluginObject,filepath);
        return result;
    }
}
