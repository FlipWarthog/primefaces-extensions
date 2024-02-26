/**
 * __PrimeFaces EChart Widget__
 *
 * Apache ECharts based components are a modern replacement for the older `<p:chart>` component. Each chart component has its
 * own model api that defines the data and the options to customize the graph.
 *
 * You can also define an extender function. The extender function allows access to the underlying
 * [echarts.js](https://echarts.apache.org/) API using the `setExtender` method of the model. You need to define
 * a global function and set it on the model, see the user guide for more details. The required typing of that function
 * is given by `PrimeFaces.widget.ExtEChart.ChartExtender`.
 */
PrimeFaces.widget.ExtEChart = PrimeFaces.widget.DeferredWidget.extend({

    /**
     * @override
     * @inheritdoc
     * @param {PrimeFaces.PartialWidgetCfg<TCfg>} cfg
     */
    init: function (cfg) {
        this._super(cfg);

        // user extension to configure gchart
        let extender = this.cfg.extender;
        if (extender) {
            if (typeof extender === "function") {
                extender.call(this);
            } else {
                PrimeFaces.error("Extender value is not a javascript function!");
            }
        }

        this.renderDeferred();
    },

    /**
     * @override
     * @inheritdoc
     * @param {PrimeFaces.PartialWidgetCfg<TCfg>} cfg
     */
    refresh: function (cfg) {
        if (this.chart) {
            this.chart.dispose();
        }

        this._super(cfg);
    },

    /**
     * @override
     * @inheritdoc
     */
    destroy: function () {
        this._super();

        if (this.chart) {
            this.chart.dispose();
        }
    },

    /**
     * @include
     * @override
     * @protected
     * @inheritdoc
     */
    _render: function () {
        // configure theme
        let theme = this.cfg.theme || PrimeFaces.env.getThemeContrast();

        // configure options
        let options = this.cfg.config;
        options.aria = {show: true};

        // initialize the chart
        this.chart = echarts.init(document.getElementById(this.id), theme);
        this.chart.setOption(options);

        this.setupResponsive();
        this.bindItemSelect();
    },

    /**
     * Setups the window resize listener to make the chart responsive.
     * @private
     */
    setupResponsive: function () {
        let $this = this;
        // make responsive
        window.addEventListener('resize', function () {
            $this.chart.resize();
        });
    },

    /**
     * Setups the event listeners required by this widget when an item (data point) in the chart is selected.
     * @private
     */
    bindItemSelect: function () {
        if (!this.hasBehavior('itemSelect')) {
            return;
        }

        let $this = this;
        this.chart.on("click", function (event) {
            let params = [];
            for (const key in event) {
                if (Object.hasOwnProperty.call(event, key)) {
                    const value = event[key];
                    if (typeof value !== 'object' && typeof value !== 'function') {
                        params.push({
                            name: key,
                            value: value
                        });
                    }
                }
            }
            $this.callBehavior("itemSelect", {params});
        });
    },
});