Feature: 首页功能
  Background:
    Given 用户已经登录
      | username | password |
      | 123456   | 123456   |
  @home
  Scenario: 进入首页
    Given 用户在首页
    When 用户获取页面标题
    Then 页面标题是"多源灾情数据管理系统"
  @home
  Scenario: 首页选项计数
    Given 用户在首页
    Then 用户获得首页选项
      | 参数设置    |
      | 震情数据    |
      | 人员伤亡及失踪 |
      | 房屋破坏    |
      | 生命线工程灾情 |
      | 次生灾害    |
      | 泛在感知数据  |
      | 群智感知灾情  |
      | 数据请求管理  |
    And 首页选项数目为 9
