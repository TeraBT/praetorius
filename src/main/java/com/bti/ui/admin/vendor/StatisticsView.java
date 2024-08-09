package com.bti.ui.admin.vendor;

import com.bti.controllers.OrderController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.color.Color;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.options.BarOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
import software.xdev.chartjs.model.options.scale.CoreScaleOptions;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.TickOptions;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianScaleOptions;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianTickOptions;
import software.xdev.chartjs.model.options.scale.cartesian.category.CategoryTickOptions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Scope("request")
public class StatisticsView {

    @Autowired
    private OrderController orderController;

    int totalOrdersCount;

    private String ordersPerMonthChart;

    private Collection<Number> getOrdersPerMonthStatistics() {
        List<Number> ordersPerMonthList = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

        orderController.getAllOrders().forEach(o -> {
            int monthIndex = o.getCreateTimestamp().getMonth().getValue() - 1;
            ordersPerMonthList.set(monthIndex, ordersPerMonthList.get(monthIndex).intValue() + 1);

        });

        return ordersPerMonthList;
    }

    @PostConstruct
    public void init() {
        totalOrdersCount = orderController.getAllOrders().size();

        ordersPerMonthChart = new BarChart()
                .setData(new BarData()
                        .addDataset(new BarDataset()
                                .setData(
                                        getOrdersPerMonthStatistics()
                                )
                                .setLabel("Orders")
                                .setBackgroundColor(new Color(0, 150, 255, 1))
                                .setBorderColor(new Color(0, 0, 0))
                                .setBorderWidth(1))
//                        .addDataset(new BarDataset()
//                                .setData(85, 69, 20, 51, 76, 75, 10)
//                                .setLabel("My Second Dataset")
//                                .setBackgroundColor(new Color(255, 159, 64, 0.2))
//                                .setBorderColor(new Color(255, 159, 64))
//                                .setBorderWidth(1)
//                        )
                        .setLabels("January", "February", "March", "April", "May", "June",
                                "July", "August", "September", "October", "November", "December"))
                .setOptions(new BarOptions()
                                .setResponsive(true)
                                .setMaintainAspectRatio(false)
                                .setIndexAxis(BarOptions.IndexAxis.X)
//                                .setScales(new Scales().addScale(Scales.ScaleAxis.Y, new CartesianScaleOptions().setTicks(
//                                        new CartesianTickOptions().setPadding(100)
//                                        )))

//                                .setBarPercentage(BigDecimal.valueOf(0.9))
//                                .setStacked(false)
//                                .setTicks(new CategoryTicks()
//                                        .setAutoSkip(true)
//                                        .setMirror(true)))
//                        )
//                        .setPlugins(new Plugins()
//                                .setTitle(new Title()
//                                        .setDisplay(true)
//                                        .setText("Orders per month")))
                ).toJson();
    }

    public int getTotalOrdersCount() {
        return totalOrdersCount;
    }

    public String getOrdersPerMonthChart() {
        return ordersPerMonthChart;
    }
}
