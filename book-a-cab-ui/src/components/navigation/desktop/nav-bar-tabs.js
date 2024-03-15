import React from "react";
import { NavBarTab } from "./nav-bar-tab";
import { useAuth0 } from "@auth0/auth0-react";

export const NavBarTabs = () => {
  const { isAuthenticated } = useAuth0();
  return (
    <div className="nav-bar__tabs">
      {isAuthenticated && <NavBarTab path="/bookings" label="My bookings" />}
    </div>
  );
};
