import AppTopbar from "@/components/layout/AppTopbar";

export default function AdminLayout({children}) {
    return (
        <>
            <AppTopbar></AppTopbar>
            {children}
        </>
    )
}
