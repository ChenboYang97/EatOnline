import { Layout, Typography } from "antd";
import { useState } from "react";
import FoodList from "./FoodList";
import LoginForm from "./LoginForm";
import MyCart from "./MyCart";
import SignupForm from "./SignupForm";

const { Header, Content, Footer } = Layout;
const { Title } = Typography;

function App() {
  const [authorized, setAuthorized] = useState(false);

  return (
    <Layout className="layout">
      <Header className="header">
        <Title id="title" level={2}>EatOnline</Title>
        <div>{authorized ? <MyCart /> : <SignupForm />}</div>
      </Header>
      <Content className="content">
        {authorized ? (
          <FoodList />
        ) : (
          <LoginForm onSuccess={() => setAuthorized(true)} />
        )}
      </Content>
      <Footer className="footer">EatOnline ©2021 Created by Chenbo Yang</Footer>
    </Layout>
  );
}

export default App;