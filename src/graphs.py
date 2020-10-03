import plotly.graph_objects as go
import numpy as np
import statistics


# x = np.array([1, 2, 3, 4, 5,6, 7, 8, 9, 10, 11, 12 , 13 ,14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24])

x = [4, 6, 8, 12]


# tempY = [8, 7546, 22521, 31114, 32774, 41605, 45176, 51572, 95768, 109257, 115542, 127505, 117686, 124800, 137041, 148445, 220429, 239764, 246847, 274934, 286692, 316411, 323295, 377155]
arr = np.array([])


throughput = [40000000 / 243000000.0, 60000000.0 /
              392000000, 80000000.0/556000000, 120000000.0/869870000]

# temp = []
# for num in tempY:
#     temp.append(num/1000.0)

arr = np.array(throughput)


# y = np.array([8, 7546, 22521, 31114, 32774, 41605, 45176, 51572, 95768, 109257, 115542, 127505, 117686, 124800, 137041, 148445, 220429, 239764, 246847, 274934, 286692, 316411, 323295, 377155])


# y = np.array([8, 7546, 22521, 31114, 32774, 41605, 45176, 51572, 95768, 109257, 115542, 127505, 117686, 1121, 1094, 1077, 771, 750, 769, 727, 732, 695, 711, 636])

fig = go.Figure()
fig.add_trace(go.Scatter(x=x, y=arr, name="linear",
                         line_shape='linear'))
# fig.add_trace(go.Scatter(x=x, y=y + 5, name="spline",
#                     text=["tweak line smoothness<br>with 'smoothing' in line object"],
#                     hoverinfo='text+name',
#                     line_shape='spline'))
# fig.add_trace(go.Scatter(x=x, y=y + 10, name="vhv",
#                     line_shape='vhv'))
# fig.add_trace(go.Scatter(x=x, y=y + 15, name="hvh",
#                     line_shape='hvh'))
# fig.add_trace(go.Scatter(x=x, y=y + 20, name="vh",
#                     line_shape='vh'))
# fig.add_trace(go.Scatter(x=x, y=y + 25, name="hv",
#                     line_shape='hv'))

# fig.update_traces(hoverinfo='text+name', mode='lines+markers')
# fig.update_layout(legend=dict(y=0.5, traceorder='reversed', font_size=16))
#
fig.update_layout(title='Throughput time for 10000000 iterations',
                  xaxis_title='Number of threads',
                  yaxis_title='throughput')


# fig.update_layout(title='System Throughput for 10000000 iterations',
#                    xaxis_title='Number of threads',
#                    yaxis_title='Throughput (output/ diff in milliseconds)')
#
fig.show()
