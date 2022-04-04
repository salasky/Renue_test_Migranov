package service.yamlEditor;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class YamlEditorImpl implements YamlEditor {
    //Получить рабочий корневой каталог, в котором работает текущая программа
    String root = System.getProperty("user.dir");
    String FileName="src\\main\\resources\\application.yml";
    //Путь к файлу свойств
    String filePath = root+File.separator+FileName;
    String column=null;

    @Override
    public String getColumn(){
        try {
            //Создаем объект свойст
            Properties properties = new Properties();
            File file = new File(filePath);
            //Загружаем свойства из файла
            properties.load(new FileInputStream(file));
            //Получаем в переменную значение конкретного свойства
            column = properties.getProperty("column");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return column;
    }

    @Override
    public void setColumn(Integer column){
        try {
            //Создаем объект свойст
            Properties properties = new Properties();
            File file = new File(filePath);
            //Загружаем свойства из файла
            properties.load(new FileInputStream(file));
            //Устанавливаем значение свойста
            properties.setProperty("column", String.valueOf(column));
            //Сохраняем свойства в файл.
            properties.store(new FileOutputStream(file), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
