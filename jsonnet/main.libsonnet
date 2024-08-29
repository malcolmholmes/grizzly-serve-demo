local g = import 'g.libsonnet';

g.dashboard.new('Example dashboard')
+ g.dashboard.withUid('example-dashboard')
+ g.dashboard.withDescription('Example Dashboard for Grizzly')
+ g.dashboard.graphTooltip.withSharedCrosshair()
+ g.dashboard.withPanels([
  g.panel.timeSeries.new('Requests / sec')
  + g.panel.timeSeries.queryOptions.withTargets([
    g.query.testData.withQueryType('randomWalk')
    + g.query.testData.withDatasource()
  ])
  + g.panel.timeSeries.standardOptions.withUnit('reqps')
  + g.panel.timeSeries.gridPos.withW(24)
  + g.panel.timeSeries.gridPos.withH(8),
])
