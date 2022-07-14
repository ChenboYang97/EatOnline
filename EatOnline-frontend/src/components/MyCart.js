import { Button, Drawer, List, message, Typography } from "antd";
import { useEffect, useState } from "react";
import { checkout, getCart, updateCart } from "../utils/apis";
import { MinusOutlined, PlusOutlined } from '@ant-design/icons';
const { Text } = Typography;

const MyCart = () => {
  const [cartVisible, setCartVisible] = useState(false);
  const [cartData, setCartData] = useState();
  const [loading, setLoading] = useState(false);
  const [checking, setChecking] = useState(false);

  const [menuId, setMenuId] = useState(0);
  const [httpMethod, setHttpMethod] = useState("POST");

  useEffect(() => {
    if (!cartVisible) {
      return;
    }

    setLoading(true);
    getCart()
      .then((data) => {
        setCartData(data);
      })
      .catch((err) => {
        message.error(err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  }, [cartVisible]);

  useEffect(() => {
    if (menuId === 0) {
      return;
    }

    setLoading(true);
    updateCart(menuId, httpMethod)
      .then((data) => {
        setCartData(data);
        message.success("Successfully update cart");
      })
      .catch((err) => {
        message.error(err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  }, [menuId, httpMethod]);

  const onCheckOut = () => {
    setChecking(true);
    checkout()
      .then(() => {
        message.success("Successfully checkout");
        setCartVisible(false);
      })
      .catch((err) => {
        message.error(err.message);
      })
      .finally(() => {
        setChecking(false);
        setMenuId(0);
      });
  };
  const onDecrease = (itemId) => {
    setMenuId(itemId);
    setHttpMethod("DELETE");
  };
  const onIncrease = (itemId) => {
    setMenuId(itemId);
    setHttpMethod("POST");
  };
  const onCloseDrawer = () => {
    setCartVisible(false);
  };
  const onOpenDrawer = () => {
    setCartVisible(true);
  };

  return (
    <>
      <Button type="primary" shape="round" onClick={onOpenDrawer}>
        Cart
      </Button>
      <Drawer
        title="My Cart"
        onClose={onCloseDrawer}
        visible={cartVisible}
        width={520}
        footer={
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
            }}
          >
            <Text strong={true}>{`Total price: $${cartData?.totalPrice}`}</Text>
            <div>
              <Button onClick={onCloseDrawer} style={{ marginRight: 8 }}>
                Cancel
              </Button>
              <Button
                onClick={onCheckOut}
                type="primary"
                loading={checking}
                disabled={loading || cartData?.orderItemList.length === 0}
              >
                Checkout
              </Button>
            </div>
          </div>
        }
      >
        <List
          loading={loading}
          itemLayout="horizontal"
          dataSource={cartData?.orderItemList}
          renderItem={(item) => (
            <List.Item>
              <List.Item.Meta
                title={item.menuItem.name}
                description={`$${item.price}`}
              />
              <Button icon={<MinusOutlined />} onClick={() => { onDecrease(item.menuItem.id); }} shape="circle"/>
              <div>  {item.quantity}  </div>
              <Button icon={<PlusOutlined />} onClick={() => { onIncrease(item.menuItem.id); }}  shape="circle"/>
            </List.Item>
          )}
        />
      </Drawer>
    </>
  );
};

export default MyCart;