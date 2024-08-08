package com.bti.ui.admin.vendor;

import jakarta.annotation.PostConstruct;
import org.primefaces.component.barchart.BarChart;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("request")
public class StatisticsView {

    private BarChart ordersPerMonthChart;

    @PostConstruct
    public void init() { // TODO: Docs outdated? Figure out what classes to use.
//        ordersPerMonthChart = new BarChart()
//                .getModel()
//                .setData(new BarChartDataSet().setData(List.of(65, 59, 80, 81, 56, 55, 40));


    }

    public BarChart getOrdersPerMonthChart() {
        return ordersPerMonthChart;
    }
}
