Feature: 系统管理员功能
  Background:
    Given 系统管理员已经登录
      | username | password |
      | 123123   | 123456   |
  @dataPersonInfo
  Scenario: 查看用户数据
    Given 系统管理员在首页
    When 系统管理员点击查看用户数据
    Then 出现查看用户数据子页面
    And 显示用户名和密码列表

  @dataPersonInfo
  Scenario Outline: 添加新的用户
    Given 系统管理员在首页
    When 系统管理员点击查看用户数据
    And 系统管理员点击添加按钮
    Then 出现添加弹窗
    And 系统管理员输入用户名 <username>
    And 系统管理员输入密码 <password>
    And 系统管理员输入确认密码 <cpassword>
    And 系统管理员选择用户类型 <type>
    And 系统管理员点击弹窗中添加按钮
    Then 提示结果信息 <info>
    Examples:
      | username | password | cpassword | type  | info |
      | 222222   | 222222   | 222222    | 数据管理员 | 注册成功 |
