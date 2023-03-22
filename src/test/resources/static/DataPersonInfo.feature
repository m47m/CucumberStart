Feature: 数据管理员个人信息功能
  Background:
    Given 数据管理员已经登录
      | username | password |
      | 123456   | 123456   |
  @dataPersonInfo
  Scenario: 查看个人信息
    Given 数据管理员在首页
    When 数据管理员点击个人中心
    And 数据管理员点击查看个人信息
    Then 出现个人信息弹窗
    And 数据管理员个人信息显示
      | username | type  |
      | 123456   | 数据管理员 |

  @dataPersonInfo
  Scenario: 切换账号
    Given 数据管理员在首页
    When 数据管理员点击个人中心
    And 数据管理员点击切换账号
    Then  用户处于登录页面

  @dataPersonInfo
  Scenario: 注销
    Given 数据管理员在首页
    When 数据管理员点击个人中心
    And 数据管理员点击注销账号
    Then 出现注销成功信息
    And 用户处于登录页面

  @dataPersonInfo
  Scenario Outline: 更改用户名
    Given 数据管理员在首页
    When 数据管理员点击个人中心
    And 数据管理员点击用户名更改
    Then 出现用户名更改弹窗
    And 数据管理员输入新的用户名 <username>
    Then 出现提示信息<info>
    Examples:
      | username | info    |
      | 123456   | 用户名未作更改 |
      | 654321   | 用户名更改成功 |

  @dataPersonInfo
  Scenario Outline: 更改密码
    Given 数据管理员在首页
    When 数据管理员点击个人中心
    And 数据管理员点击密码更改
    Then 出现密码更改弹窗
    And 数据管理员输入新的密码 <password>
    And 数据管理员点击更改按钮
    Then 出现提示信息<info>
    Examples:
      | password | info   |
      | 123456   | 密码未作更改 |
      | 654321   | 密码更改成功 |