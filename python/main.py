from grafana_foundation_sdk.builders import dashboard, testdata, timeseries
from grafana_foundation_sdk.models.dashboard import DataSourceRef, DashboardCursorSync, DashboardLinkType
from grafana_foundation_sdk.cog.encoder import JSONEncoder

def makeDashboard():
  builder = (
    dashboard.Dashboard('Example dashboard')
    .uid('example-dashboard')
    .description('Example Dashboard for Grizzly')
    .tooltip(DashboardCursorSync.CROSSHAIR)
    .with_panel(
      timeseries.Panel()
      .title('Requests / sec')
      .unit("reqps")
      .with_target(
        testdata.Dataquery().query_type('randomWalk')
      )
      .span(24)
      .height(8)
    )
  );
  
  dash = JSONEncoder(sort_keys=True, indent=2).encode(builder.build())
  return dash

print(makeDashboard())  