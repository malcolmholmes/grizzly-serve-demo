package main

import (
	"encoding/json"
	"fmt"
	
	"github.com/grafana/grafana-foundation-sdk/go/dashboard"
	"github.com/grafana/grafana-foundation-sdk/go/testdata"
	"github.com/grafana/grafana-foundation-sdk/go/timeseries"
)

func makeDashboard() string {
	builder := dashboard.NewDashboardBuilder("Example dashboard").
		Uid("example-dashboard").
		Description("Example Dashboard for Grizzly").
		Tooltip(dashboard.DashboardCursorSyncCrosshair).
		WithPanel(
			timeseries.NewPanelBuilder().
				Title("Requests / sec").
				Unit("reqps").
				WithTarget(
					testdata.NewDataqueryBuilder().QueryType("randomWalk").
						Datasource(testdata.NewDatasourceBuilder().Uid("grafana").Type("grafana")),
				).
				Span(24).
				Height(8),
		)
	dashboard, err := builder.Build()
	if err != nil {
		panic(err)
	}
	
	dashboardJson, err := json.MarshalIndent(dashboard, "", "  ")
	if err != nil {
		panic(err)
	}
	return string(dashboardJson)
}

func main() {
	fmt.Println(makeDashboard())
}
