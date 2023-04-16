import React from "react";
import Chart from "react-apexcharts";



function Bar() {

    const series = [ //data on the y-axis
        {
            name: "Month-wise garbage Data",
            data: [11, 21, 30, 32, 42, 54, 67, 75, 84, 44, 10, 12]
        }
    ];
    const options = {
        chart: { id: 'bar-chart' },
        xaxis: {
            categories: ['jan', 'feb', 'March', 'April', 'May', 'June', 'July', 'August', 'Sept', 'oct', 'Nov', 'Dec']
        }
    }

    return (
        <div className="app">
            <div className="row">
                <div className="mixed-chart" >
                    <Chart

                        type="bar"
                        width="100%"
                        height="225"
                        series={series}
                        options={options}
                    />
                </div>
            </div>

        </div>
    );


}
export default Bar;