package com.example.myapplication;


import android.graphics.*;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.view.LineChartView;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
public class chart1 extends AppCompatActivity {

    public LineChartView lineChart;

    ArrayList<String> columnList = new ArrayList<>();
    ArrayList<String> valueList = new ArrayList<>();
//    String[] date = new String[columnList.size()];
//    double[] score = new double[columnList.size()];

    String[] date = {"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22","5-23","5-20"};//X轴的标注
//    String[] date = {"25/03", "26/03", "27/03", "28/03", "29/03", "30/03", "31/03", "01/04", 02/04, 03/04, 04/04, 05/04, 06/04, 07/04, 08/04, 09/04, 10/04, 11/04, 12/04, 13/04, 14/04, 15/04, 16/04, 17/04, 18/04, 19/04, 20/04, 21/04, 22/04, 23/04, 24/04, 25/04, 26/04, 27/04, 28/04, 29/04, 30/04, 01/05, 02/05, 03/05, 04/05, 05/05, 06/05, 07/05, 08/05, 09/05, 10/05, 11/05, 12/05, 13/05, 14/05, 15/05, 16/05, 17/05, 18/05, 19/05, 20/05, 21/05, 22/05, 23/05, 24/05, 25/05, 26/05, 27/05, 28/05, 29/05, 30/05, 31/05, 01/06, 02/06, 03/06, 04/06, 05/06, 06/06, 07/06, 08/06, 09/06, 10/06, 11/06, 12/06, 13/06, 14/06, 15/06, 16/06, 17/06, 18/06, 19/06, 20/06, 21/06, 22/06, 23/06, 24/06, 25/06, 26/06, 27/06, 28/06, 29/06, 30/06, 01/07, 02/07, 03/07, 04/07, 05/07, 06/07, 07/07, 08/07, 09/07, 10/07, 11/07, 12/07, 13/07, 14/07, 15/07, 16/07, 17/07, 18/07, 19/07, 20/07, 21/07, 22/07, 23/07, 24/07, 25/07, 26/07, 27/07, 28/07, 29/07, 30/07, 31/07, 01/08, 02/08, 03/08, 04/08, 05/08, 06/08, 07/08, 08/08, 09/08, 10/08, 11/08, 12/08, 13/08, 14/08, 15/08, 16/08, 17/08, 18/08, 19/08, 20/08, 21/08, 22/08, 23/08, 24/08, 25/08, 26/08, 27/08, 28/08, 29/08, 30/08, 31/08, 01/09, 02/09, 03/09, 04/09, 05/09, 06/09, 07/09, 08/09, 09/09, 10/09, 11/09, 12/09, 13/09, 14/09, 15/09, 16/09, 17/09, 18/09, 19/09, 20/09, 21/09, 22/09, 23/09, 24/09, 25/09, 26/09, 27/09, 28/09, 29/09, 30/09]};//X轴的标注
    float[] score= {50,42,90,33,10,74,22,18,79,20};//图表的数据点
//    float[] score= {846.9, 810.6, 833.8, 796.5, 772.9, 438.5, 437.5, 825.3, 865.2, 839.9, 845.7, 804.6, 461.5, 475.3, 853.1, 856.2, 834.8, 838.7, 783.8, 471, 474, 846.9, 822.2, 848.1, 757.3, 421.4, 427.7, 421.4, 432.5, 759.5, 773.2, 760.8, 735, 451.4, 421.3, 839.7, 805.8, 820, 766.8, 786, 462.3, 454.8, 501.8, 844.6, 833.4, 803.6, 814.1, 440.9, 452.7, 827.2, 801, 796.3, 760.5, 760.8, 484.2, 469.8, 799.4, 778, 844.4, 758.1, 720.4, 390.8, 404.1, 444.4, 746, 755.3, 721.5, 687.8, 410.2, 404.5, 788.9, 840, 831.7, 785.9, 738.6, 445.4, 438.8, 824, 828.2, 834.9, 809.9, 754.2, 449.8, 455, 834.2, 800.7, 818.9, 785.9, 767.4, 449.4, 458.8, 825, 787.9, 812.4, 776.7, 702.7, 392.3, 398.7, 779, 739.6, 781.3, 752.2, 707.6, 383.9, 395, 754.6, 740.5, 766.9, 741.8, 694.1, 383.9, 391.1, 795.3, 785.1, 778.2, 728.9, 684.1, 410.4, 404.1, 767.3, 786.7, 818.8, 817.2, 739.7, 432.4, 424.8, 787.7, 757.9, 743.9, 736.2, 723.8, 403, 414.8, 771.4, 756, 766, 725.5, 702.4, 375.5, 380.8, 718.5, 705.8, 725, 699.7, 678.1, 388.5, 382.3, 754, 722, 721.6, 728.5, 726.1, 391.7, 402.5, 430.6, 814.9, 825.2, 723.3, 689.4, 393.4, 386.3, 746, 731.8, 779.3, 756.7, 708.7, 406.3, 401.3, 747.7, 742.8, 732.2, 716, 666.7, 406, 386.7, 734.3, 718.8, 746.8, 713.8, 723.8, 411.6, 398.4, 750, 768.8, 734.9, 716, 694.1, 376.1, 372.4, 744.8};
    public List<PointValue> mPointValues = new ArrayList<>();
    public List<AxisValue> mAxisXValues = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart1);
        try {

            //获取指定列的值
            readSpecifyColumns(new File("src/main/res/raw/dataset1.xls"));

            //获取指定行的值
//            readSpecifyRows(new File("E:\\STUDY\\大二\\大二下\\资产管理项目\\Dataset 1.xls"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        lineChart = findViewById(R.id.line_chart);//画图

        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
    }

    public void readSpecifyColumns(File file) throws Exception {

        Workbook readwb;
        InputStream io = new FileInputStream(file.getAbsoluteFile());
        readwb = Workbook.getWorkbook(io);
        Sheet readsheet = readwb.getSheet(0);
        int rsRows = readsheet.getRows();  //获取表格行数
        for (int i = 1; i < rsRows; i++) {
            Cell cell_name = readsheet.getCell(1, i);  //第一列的值
            columnList.add(cell_name.getContents());
            Cell cell_value = readsheet.getCell(2, i);  //第三列的值，此处需要手动更改，获取不同列的值
            valueList.add(cell_value.getContents());
        }
//        System.out.println(columnList);
//        System.out.println(valueList);

//        for (int i = 0; i < columnList.size(); i++) {
//            date[i] = columnList.get(i);
//            score[i] = Float.parseFloat(valueList.get(i));
////			System.out.println("<string name=" + "\"" + name_String[i] + "\">" + value_String[i] +  "</string>");
//        }
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }
    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, (float) score[i]));
        }
    }

    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(false);//曲线的数据坐标是否加上备注
        //      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
//        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线
        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 10;
        lineChart.setCurrentViewport(v);
    }
}