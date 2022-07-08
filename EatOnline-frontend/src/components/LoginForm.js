import { Button, Form, Input, message } from "antd";
import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { login } from "../utils/apis";

class LoginForm extends React.Component {
    state = {
        loading: false,
    };

    onFinish = (data) => {
        this.setState({
            loading: true,
        });
        login(data)
            .then(() => {
                message.success("Login Successful!");
                this.props.onSuccess();
            })
            .catch(error => {
                message.error(error);
            })
            .finally(() => {
                this.setState({
                    loading: false,
                })
            })
    }

    render = () => {
        return (
            <Form
                name="normal_login"
                className="login_form"
                onFinish={ this.onFinish }
            >
                <Form.Item
                    label="Username"
                    name="username"
                    rules={[
                    {
                        required: true,
                        message: 'Please input your username!',
                    },
                    ]}
                >
                    <Input prefix = { <UserOutlined /> }/>
                </Form.Item>

                <Form.Item
                    label="Password"
                    name="password"
                    rules={[
                    {
                        required: true,
                        message: 'Please input your password!',
                    },
                    ]}
                >
                    <Input.Password prefix = { <LockOutlined /> }/>
                </Form.Item>

                <Form.Item
                    wrapperCol={{
                    offset: 8,
                    span: 16,
                    }}
                >
                    <Button type="primary" htmlType="submit" loading = { this.state.loading }>
                    Login
                    </Button>
                </Form.Item>
            </Form>
        )
    }
}

export default LoginForm;