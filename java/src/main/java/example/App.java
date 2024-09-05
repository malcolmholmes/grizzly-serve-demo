package example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.grafana.foundation.dashboard.Dashboard;
import com.grafana.foundation.dashboard.DashboardCursorSync;
import com.grafana.foundation.testdata.Dataquery;
import com.grafana.foundation.testdata.Datasource;
import com.grafana.foundation.timeseries.PanelBuilder;

public class App {

    public static void main(String[] args) {
       Dashboard dashboard = new Dashboard.Builder("Example Dashboard").
            uid("example-dashboard").
            description("Example Dashboard for Grizzly").
            tooltip(DashboardCursorSync.CROSSHAIR).
            withPanel(
                new PanelBuilder().
                        title("Requests / sec").
                        unit("reqps").
                        span(24).
                        height(8).
                        withTarget(
                            new Dataquery.Builder().
                                datasource(
                                    new Datasource.Builder().
                                        uid("grafana").
                                        type("grafana")
                                ).
                                queryType("randomWalk")
                        )
            ).build();

        try {
            System.out.println(dashboard.toJSON());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
