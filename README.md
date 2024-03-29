### Conch-Geo

> 地理位置解析服务，可供爬虫使用！供参考学习！

##### 本项目基于Conch-Boot开发

##### 功能：
- 提供指定城市的经纬度生成，精度可控。
- 搜索逆向经纬度（逆地理编码）信息
    - 逆地理编码：将经纬度转换为详细结构化的地址
    - 例如：116.480881,39.989410 转换地址描述后：北京市朝阳区阜通东大街6号

##### 覆盖城市：

| 城市              | 维度1(度) | 维度2(度) | 维度3(量) | 维度4(量) | 经度1(度) | 经度2(度) | 经度3(量) | 经度4(量)    |
| ----------------  | -------- | -------- | -------- | -------- | --------- | -------- | -------- | ---------- |
| 北京市            | 39.26 | 41.03 | 39.4333 | 41.0500 | 115.25 | 117.30 | 115.4167 | 117.5000    |
| 天津市            | 38.34 | 40.15 | 38.5827 | 40.2531 | 116.43 | 118.19 | 116.7327 | 118.2364    |
| 上海市            | 30.40 | 31.53 | 30.6667 | 31.8833 | 120.51 | 122.12 | 120.8500 | 122.2000    |
| 重庆市            | 28.10 | 32.13 | 28.1667 | 32.2167 | 105.17 | 110.11 | 105.2833 | 110.1833    |
| 香港特别行政区     | 22.09 | 22.37 | 22.1500 | 22.6167 | 113.52 | 114.30 | 113.8667 | 114.5000    |
| 澳门特别行政区     | | | | | | | |
| 西藏自治区        | 26.73 | 36.53 | 26.7333 | 36.5333 | 78.41 | 99.10 | 78.4167 | 99.1000    |
| 广西壮族自治区     | 20.54 | 26.23 | 20.9161 | 26.3994 | 104.29 | 112.04 | 104.4864 | 112.0827    |
| 内蒙古自治区       | 37.24 | 53.23 | 37.4161 | 53.3869 | 97.12 | 126.04 | 97.2161 | 126.0702    |
| 宁夏回族自治区     | 35.14 | 39.23 | 35.2494 | 39.8758 | 104.17 | 107.39 | 104.2869 | 107.6536    |
| 新疆维吾尔自治区   | 34.22 | 49.10 | 34.3667 | 49.1667 | 73.40 | 96.23 | 73.6667 | 96.3833    |
| 湖南省            | 24.38 | 30.80 | 24.6333 | 31.3333 | 108.47 | 114.15 | 108.7833 | 114.2500    |
| 台湾省            | 20.45 | 25.56 | 20.7661 | 25.9369 | 119.18 | 124.34 | 119.3036 | 124.5703    |
| 海南省            | 18.10 | 20.10 | 18.1697 | 20.1697 | 108.37 | 111.05 | 108.6203 | 111.0994    |
| 云南省            | 21.13 | 29.25 | 21.1333 | 29.2500 | 97.51  | 106.18 | 97.5167  | 106.1833    |
| 广东省            | 20.12 | 25.31 | 20.2000 | 25.5100 | 109.45 | 117.20 | 109.7500 | 117.3340    |
| 福建省            | 23.30 | 28.22 | 23.5161 | 28.3702 | 115.50 | 120.40 | 115.8494 | 120.6702    |
| 贵州省            | 24.37 | 29.13 | 24.6327 | 29.2203 | 103.36 | 109.35 | 103.6161 | 109.5869    |
| 江西省            | 24.29 | 30.04 | 24.1327 | 29.1536 | 113.34 | 118.28 | 114.0494 | 118.4703    |
| 浙江省            | 27.12 | 31.31 | 27.2161 | 31.5203 | 118.00 | 123.00 | 118.0000 | 123.0000    |
| 四川省            | 26.03 | 32.19 | 26.0661 | 34.3203 | 97.21  | 108.31 | 97.3661  | 108.5329    |
| 湖北省            | 29.05 | 33.20 | 29.0994 | 33.3364 | 108.21 | 116.07 | 108.3661 | 116.1327    |
| 安徽省            | 29.41 | 34.38 | 29.6864 | 34.6494 | 114.54 | 119.37 | 114.9161 | 119.6203    |
| 江苏省            | 30.45 | 35.20 | 30.7531 | 35.3364 | 116.18 | 121.57 | 116.3036 | 121.9661    |
| 青海省            | 31.41 | 39.07 | 31.4161 | 39.0703 | 89.41  | 103.07 | 89.4161  | 103.0702    |
| 甘肃省            | 32.11 | 42.57 | 32.1864 | 42.9661 | 92.13  | 108.46 | 92.2327  | 108.7697    |
| 陕西省            | 31.42 | 39.35 | 31.7030 | 39.5864 | 105.29 | 111.15 | 105.4864 | 111.2661    |
| 山西省            | 34.34 | 40.43 | 34.5827 | 40.7203 | 110.14 | 114.33 | 110.2494 | 114.5536    |
| 河南省            | 31.23 | 36.22 | 31.3994 | 36.3827 | 110.21 | 116.39 | 110.3661 | 116.6531    |
| 河北省            | 36.01 | 42.37 | 36.0327 | 42.6203 | 113.04 | 119.53 | 113.0827 | 119.8669    |
| 山东省            | 34.22 | 38.23 | 34.3827 | 38.3869 | 114.19 | 122.43 | 114.3327 | 122.7203    |
| 辽宁省            | 38.43 | 43.26 | 38.7327 | 43.4369 | 118.53 | 125.46 | 118.8994 | 125.7703    |
| 吉林省            | 40.52 | 46.18 | 40.8827 | 46.3036 | 121.38 | 131.19 | 121.6494 | 131.3203    |
| 黑龙江省          | 43.25 | 53.33 | 43.4167 | 53.5500 | 121.11 | 135.05 | 121.1830 | 135.0833    |


##### 使用
> 1. 下载[数据文件](https://github.com/beihu-stack/conch-geo/releases/download/v1.0.0/china-region.json.zip)
> 2. 解压数据文件，放入项目的 resources 文件夹中
> 3. 修改配置文件中的特殊配置
> 4. 启动

##### TODO：

- 集成百度地图


##### 重要：

本项目用于地理位置的生成、逆向经纬度解析，包含本人的一些拙劣代码，如有意见，尽情提Issue。
涉及相关大牛代码部分已保留完整源作者信息，如您确定影响到您的个人权益，请联系我立刻下线，比心！

###### 官方：

`[addrparser](https://github.com/hsp8712/addrparser)`

`[Conch-Boot](https://github.com/beihu-stack/conch-boot)`

###### 大神：

`[Api-Boot](https://github.com/minbox-projects/api-boot)`

`[tangcent](https://github.com/tangcent)` 

#### 如果你觉得写得差强人意，麻烦给一个小星星！![yes](assets/star.png)
