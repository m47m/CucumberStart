Feature: 登录页面功能
  @login
  Scenario Outline: 使用正确的信息登录
    Given 用户处于登录页面
    When 用户输入用户名 <username>和密码 <password>
    And 用户点击登录按钮
    Then 用户获取页面标题
    And 页面标题是"多源灾情数据管理系统"
    Examples:
      | username | password |
      | 123456   | 123456   |
      | 123123   | 123456   |

