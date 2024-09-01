<?php

//namespace Dashboard\Example;

require __DIR__ . '/vendor/autoload.php';

use Grafana\Foundation\Common;
use Grafana\Foundation\Common\GraphDrawStyle;
use Grafana\Foundation\Common\GraphGradientMode;
use Grafana\Foundation\Common\GraphThresholdsStyleConfigBuilder;
use Grafana\Foundation\Common\GraphThresholdsStyleMode;
use Grafana\Foundation\Common\LegendDisplayMode;
use Grafana\Foundation\Common\LegendPlacement;
use Grafana\Foundation\Common\LineInterpolation;
use Grafana\Foundation\Common\SortOrder;
use Grafana\Foundation\Common\TooltipDisplayMode;
use Grafana\Foundation\Common\VisibilityMode;
use Grafana\Foundation\Common\VizLegendOptionsBuilder;
use Grafana\Foundation\Common\VizTooltipOptionsBuilder;
use Grafana\Foundation\Dashboard as SDKDashboard;
use Grafana\Foundation\Dashboard\DashboardBuilder;
use Grafana\Foundation\Dashboard\DashboardCursorSync;
use Grafana\Foundation\Dashboard\DashboardLinkBuilder;
use Grafana\Foundation\Dashboard\DashboardLinkType;
use Grafana\Foundation\Dashboard\RowBuilder;
use Grafana\Foundation\Dashboard\TimePickerBuilder;
use Grafana\Foundation\Testdata;
use Grafana\Foundation\Timeseries;

function makeDashboard(): string {
    $builder = (new DashboardBuilder(title: 'Example Dashboard'))
		->uid('example-dashboard')
		->description('Example Dashboard for Grizzly')
                ->tooltip(DashboardCursorSync::crosshair())
		->withPanel(
			(new Timeseries\PanelBuilder())
			->title('Requests / sec')
			->unit('reqps')
			->withTarget(
				(new Testdata\DataqueryBuilder())
			            ->queryType('randomWalk')
			            ->datasource((new Testdata\DatasourceBuilder())->type('grafana')->uid('grafana'))
			  )
			->span(24)
			->height(8)
		);
    $json = $builder->build();

	return json_encode($json, JSON_PRETTY_PRINT).PHP_EOL;
}

echo makeDashboard();
