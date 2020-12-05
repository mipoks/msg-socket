package ru.itis.typergame.client.visualizer.eventListImpl;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import lombok.extern.slf4j.Slf4j;
import ru.itis.typergame.client.visualizer.EventListener;
import ru.itis.typergame.protocol.Record;

import java.util.Collection;

@Slf4j
public class RecordsCodePrinter implements EventListener<Collection<Record>> {
    private BarChart chart;
    private Text text;
    private XYChart.Series dataSeries1;

    public RecordsCodePrinter(BarChart chart) {

        this.chart = chart;
        dataSeries1 = new XYChart.Series();
    }

    @Override
    public synchronized void onEventAction(Collection<Record> object) {
        Platform.runLater(() -> {
            chart.getData().remove(0, chart.getData().size());


            object.forEach(x -> {
                log.info("");
                log.info("Прихдящая запись {}  ,  {}", x.getName(), x.getTime());
                dataSeries1.getData().add(new XYChart.Data(x.getName(), x.getTime()));
            });
            chart.getData().add(dataSeries1);


        });


    }
}
