package wykres;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private LineChart<?,?> lineChart;
    XYChart.Series series = new XYChart.Series();
    @FXML private TextField rangeFromTxtField;
    @FXML private TextField rangeToTxtField;
    @FXML private TextField Dx;
    @FXML private TextField polynomial;
    private int rangeFrom;
    private int rangeTo;
    private double dx;
    Pattern compiledPattern = Pattern.compile("(\\+|\\-)(\\d*)(\\w)(\\^)?(\\-?\\d*)");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lineChart.getData().addAll(series);
    }
    public void draw(){
        series.getData().clear();
        rangeFrom = Integer.parseInt(rangeFromTxtField.getText());
        rangeTo = Integer.parseInt(rangeToTxtField.getText());
        dx = Double.parseDouble(Dx.getText());
        for(double i=rangeFrom; i<=rangeTo; i+=dx){
            series.getData().add(new XYChart.Data(i, polynomial(i)));
        }
    }
    public double polynomial(double x){
        String poly = polynomial.getText();
        if(poly.charAt(0)!='-')
            poly = "+" + poly;
        Matcher matcher = compiledPattern.matcher(poly);

        double result=0;
        while (matcher.find()) {
            if (matcher.group(1).equals("-")) {
                if (matcher.group(2).equals("")) {
                    if (matcher.group(5).equals("")) result -= 1 * x;
                    else result -= 1 * Math.pow(x, Double.parseDouble(matcher.group(5)));
                } else {
                    if (matcher.group(5).equals("")) result -= Double.parseDouble(matcher.group(2)) * x;
                    else
                        result -= Double.parseDouble(matcher.group(2)) * Math.pow(x, Double.parseDouble(matcher.group(5)));
                }
            } else {
                if (matcher.group(2).equals("")) {
                    if (matcher.group(5).equals("")) result += 1 * x;
                    else result += 1 * Math.pow(x, Double.parseDouble(matcher.group(5)));
                } else {
                    if (matcher.group(5).equals("")) result += Double.parseDouble(matcher.group(2)) * x;
                    else
                        result += Double.parseDouble(matcher.group(2)) * Math.pow(x, Double.parseDouble(matcher.group(5)));
                }

            }
        }
        return result;
    }
}
