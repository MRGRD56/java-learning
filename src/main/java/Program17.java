import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class Program17 {
    public static void main(String[] args) {
        try (SparkSession spark = SparkSession.builder()
                .appName("CSV to ORC Conversion")
                .master("local")
                .getOrCreate()) {

            String inputCsvFile = "C:\\Users\\SU\\OneDrive\\Рабочий стол\\DATA\\crawdev\\_csv_to_orc\\commonFormat.csv";
            Dataset<Row> csvDf = spark.read()
                    .format("csv")
                    .option("header", "true")
                    .option("inferSchema", "true")
                    .option("sep", "|")
                    .load(inputCsvFile);

            String outputOrcFile = "C:\\Users\\SU\\OneDrive\\Рабочий стол\\DATA\\crawdev\\_csv_to_orc\\commonFormat.orc";
            csvDf.coalesce(1)
                    .write()
                    .mode(SaveMode.Overwrite)
                    .orc(outputOrcFile);
        }
    }
}
