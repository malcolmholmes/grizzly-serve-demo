package example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grafana.foundation.dashboard.Dashboard;
import com.grafana.foundation.dashboard.DashboardCursorSync;
import com.grafana.foundation.testdata.Dataquery;
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
                            new Dataquery.Builder().queryType("randomWalk")
                        )
            ).build();

        try {
            System.out.println(dashboard.toJSON());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
