Feature: 注册功能
  @register
  Scenario Outline: 数据管理员使用用户名和密码进行注册
    Given 用户处于登录页面
    When 用户点击用户注册按钮
    And 用户新建用户名 <username> 和密码 <password>
    And 用户输入确认密码 <cpassword>
    And 用户点击注册按钮
    Then 页面提示信息
    Examples:
      | username | password | cpassword |  |
      | 111111   | 111111   | 111111    |  |


