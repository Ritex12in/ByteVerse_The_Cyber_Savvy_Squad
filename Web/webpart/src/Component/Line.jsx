import React from "react";
import Chart from "react-apexcharts";



function Line() {

    const series = [ //data on the y-axis
        {
            name: "Month-wise garbage Data",
            data: [110, 210, 300, 320, 420, 540, 670, 750, 840, 440]
        }
    ];
    const options = {
        chart: { id: 'bar-chart' },
        xaxis: {
            categories: ['2020', '2021', '2022', '2023', '2024', '2025', '2026', '2027', '2028', '2029']
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
export default Line;