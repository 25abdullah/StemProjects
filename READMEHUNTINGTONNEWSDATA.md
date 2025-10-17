# **Data Journalism & Visualization Portfolio**
Data analysis and visualization projects examining public safety and housing trends in Boston using Python, Flourish, and Datawrapper.

---

## **Project 1: Boston Shooting Data Analysis (2015-2024)**

### **Overview**
Analysis of Boston shooting data identifying trends in fatal and non-fatal incidents. Uses pandas for data cleaning and matplotlib for visualization.
Data can be found here: https://data.boston.gov/dataset/shootings

### **Key Findings**
- **47% reduction** in total shootings from 2015 to 2024 (245 → 129 incidents)
- **2,141 total incidents** over 10 years (15.65% fatal, 83.42% non-fatal)
- **COVID-era spike** in 2020, peaking at 276 incidents
- **Steady decline** from 2020 through 2024

### **Key Implementation**
- **Data Processing:** Boolean indexing and datetime filtering for year/type analysis
- **Statistical Calculations:** Percent change, fatality rates, year-over-year comparisons
- **Visualizations:** Three matplotlib bar charts tracking fatal, non-fatal, and total shootings over time

### **Skills Used**
- **Data Analysis:** Pandas filtering, temporal pattern recognition
- **Statistical Thinking:** Trend analysis, anomaly identification (COVID spike)
- **Visualization Design:** Color-coded charts with clear legends and axis labels

### **Technologies Used**
Python (pandas, matplotlib), Jupyter Notebook, CSV export

---

## **Project 2: Greater Boston Housing Vacancy Rate Analysis**

### **Overview**
Dual-platform visualization examining Real-Time Vacancy Rate (RTVR) increases across Greater Boston neighborhoods since 2024.
Data can be found here: https://bostonpads.com/real-time-data/

### **Key Findings**
- **Symphony**: Largest increase (~183%)
- **Fort Hill**: Second-largest increase (~85%)
- **Central Boston concentration**: Downtown neighborhoods saw more dramatic shifts
- **Allston omitted**: Negative vacancy rate change (transparent data handling)

---

### **Visualization 1: Flourish Bar Chart**

**Key Implementation:**
- Five-neighborhood comparison with percentage-based scale (0-200%)
- Deep red bars for visual consistency
- Annotation explaining data decisions

**Skills Used:**
- **Data Storytelling:** Clear headline and appropriate scale selection
- **Journalistic Integrity:** Transparent documentation of omitted data

---

### **Visualization 2: Datawrapper Interactive Map**

**Key Implementation:**
- Geographic bubble map with proportional sizing
- Color gradient: teal (3%) to dark blue (183%)
- Neighborhood labels and boundary overlays

**Skills Used:**
- **Geographic Visualization:** Translating data into spatial patterns
- **Pattern Recognition:** Revealing downtown concentration through clustering

**Interactive Features:** Hover tooltips, geographic positioning, visual clustering

---

### **Design Strategy**
**Dual Approach:** Bar chart shows magnitude rankings; map reveals geographic patterns. Together, they provide complementary perspectives on housing trends.

**Design Decisions:**
- Proportional bubble sizing for visual impact
- Teal-to-blue gradient showing increasing intensity
- Consistent red branding for data story

### **Technologies Used**
**Flourish:** Bar chart creation, responsive design  
**Datawrapper:** Bubble maps, custom color scales

### **Data Sources**
Boston Pads rental platform (RTVR/RTAR metrics, 2024 baseline)

---

## **Combined Skills Demonstrated**

### **Technical**
- Python (pandas, matplotlib, NumPy)
- Data cleaning and temporal analysis
- Statistical calculations and trend identification
- Interactive and static visualization design

### **Data Journalism**
- Real-world dataset analysis (Boston shootings, housing data)
- Clear sourcing and attribution
- Ethical data handling and transparency
- Translating complex findings into accessible narratives

### **Analytical**
- Pattern recognition (COVID spike, geographic clustering)
- Statistical reasoning (47% reduction, 15.65% fatality rate)
- Comparative analysis across years and neighborhoods

---


## **About This Work**
Created as **Data Editor for The Huntington News** at Northeastern University. Demonstrates data science, journalism, and visual storytelling for the diverse Northeastern University community.

**Portfolio by Abdullah Ismail**  
Data Editor, The Huntington News | CS & Math, Northeastern University
